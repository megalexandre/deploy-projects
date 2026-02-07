package projects.core.usecase

import org.springframework.stereotype.Service
import projects.core.model.Project

@Service
class ProjectCreateUseCase {

    fun execute(project: Project): Project {
        return Project(
            id = project.id,
            name = project.name
        )
    }
}