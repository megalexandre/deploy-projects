package projects.web.port.`in`

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import projects.core.model.Project
import java.util.*

class ProjectCreateRequest (

    @field:NotBlank(message = "{project.name.notBlank}")
    @field:Size(min = 2, message = "{project.name.size}")
    @field:Size(max = 250, message = "{project.name.size}")
    val name: String?
){
    fun toDomain() = Project(
        id = UUID.randomUUID().toString(),
        name = this.name!!
    )
}