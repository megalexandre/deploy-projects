package projects.resources

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import projects.core.model.Project
import projects.core.respository.ProjectRepository
import projects.infra.security.CurrentUserProvider
import projects.resources.persistence.ProjectEntity
import java.util.*

@Repository
class ProjectRepositoryImpl(
    private val jpa: SpringDataProjectRepository,
    private val currentUserProvider: CurrentUserProvider,
): ProjectRepository {

    override fun save(project: Project): Project  =
         jpa.save(ProjectEntity.from(project)).toDomain()

    override fun delete(projectId: String) {
        val key = UUID.fromString(projectId)

        if (jpa.existsById(key)) {
            jpa.deleteById(key)
        }
    }

    override fun findAll(): List<Project>{
        currentUserProvider.getCurrentUser()?.userId
        return jpa.findAll(Sort.by(Sort.Direction.ASC, "id")).map { it.toDomain() }
    }

    override fun findById(projectId: String): Project? =
        jpa.findById(UUID.fromString(projectId)).orElse(null)?.toDomain()

}

@Repository
interface SpringDataProjectRepository : JpaRepository<ProjectEntity, UUID>{
}