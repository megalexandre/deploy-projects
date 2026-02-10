package projects.core.usecase.project

import org.springframework.stereotype.Service
import projects.core.model.Project
import projects.core.respository.ProjectRepository

@Service
class ProjectFindByIdUseCase(
    private val projectRepository: ProjectRepository
) {
    fun execute(id: String): Project? = projectRepository.findById(id)
}