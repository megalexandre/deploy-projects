package projects.core.usecase

import org.springframework.stereotype.Service
import projects.core.model.Project
import projects.core.respository.ProjectRepository

@Service
class ProjectCreateUseCase(
    private val projectRepository: ProjectRepository
) {

    fun execute(project: Project): Project =
        projectRepository.save(project)

}