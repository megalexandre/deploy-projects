package projects.core.usecase

import org.springframework.stereotype.Service
import projects.core.model.Project

@Service
class ProjectCreateUseCase {

    fun execute(project: Project): Project = project.copy()

}