package projects.web.port.out

import projects.core.model.Project

class ProjectCreateResponse (
    val id: String,
    val name: String
)

fun Project.toResponse() = ProjectCreateResponse(
    id = this.id,
    name = this.name
)