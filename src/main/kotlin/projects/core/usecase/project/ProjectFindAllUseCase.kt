package projects.core.usecase.project

import org.springframework.stereotype.Service
import projects.core.model.Project
import projects.core.respository.ProjectRepository

@Service
class ProjectFindAllUseCase(
    private val projectRepository: ProjectRepository
) {
    fun execute(): List<Project> =  projectRepository.findAll()
}
