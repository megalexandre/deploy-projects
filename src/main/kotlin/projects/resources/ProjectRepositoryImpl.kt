package projects.resources

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import projects.core.model.Project
import projects.core.respository.ProjectRepository
import projects.resources.persistence.ProjectEntity

@Repository
class ProjectRepositoryImpl(
    private val jpa: SpringDataProjectRepository
): ProjectRepository {

    override fun save(project: Project): Project  =
         jpa.save(ProjectEntity.from(project)).toDomain()

    override fun delete(projectId: String) {
        if (jpa.existsById(projectId)) {
            jpa.deleteById(projectId)
        }
    }

    override fun findAll(): List<Project> =
        jpa.findAll(Sort.by(Sort.Direction.ASC, "id")).map { it.toDomain() }

    override fun findById(projectId: String): Project? =
        jpa.findById(projectId).orElse(null)?.toDomain()

}

@Repository
interface SpringDataProjectRepository : JpaRepository<ProjectEntity, String>