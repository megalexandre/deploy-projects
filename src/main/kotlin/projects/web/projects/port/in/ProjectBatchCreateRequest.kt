package projects.web.projects.port.`in`

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty

data class ProjectBatchCreateRequest(
    @field:JsonProperty("projetos")
    @field:NotEmpty(message = "{project.batch.notEmpty}")
    @field:Valid
    val projects: List<ProjectCreateRequest>
)
