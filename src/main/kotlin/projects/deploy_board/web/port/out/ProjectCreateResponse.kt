package projects.deploy_board.web.port.out

import projects.deploy_board.core.model.Project

class ProjectCreateResponse (
    val id: String,
    val name: String
)

fun Project.toResponse() = ProjectCreateResponse(
    id = this.id,
    name = this.name
)