package projects.web.port.out

import projects.core.model.Project

class ProjectCreateResponse (
    val id: String,
)

fun Project.toResponse() = ProjectCreateResponse(
    id = this.id,
)