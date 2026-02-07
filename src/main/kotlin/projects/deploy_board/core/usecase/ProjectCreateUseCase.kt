package projects.deploy_board.core.usecase

import org.springframework.stereotype.Service
import projects.deploy_board.core.model.Project

@Service
class ProjectCreateUseCase {

    fun execute(project: Project): Project {
        return Project(
            id = project.id,
            name = project.name
        )
    }
}