package projects.web.services.port.out

import com.fasterxml.jackson.annotation.JsonProperty
import projects.core.usecase.project.BatchImportResult
import projects.web.projects.port.out.ProjectCreateResponse
import projects.web.projects.port.out.toResponse

data class ProjectBatchCreateResponse(
    @JsonProperty("totalProcessado")
    val totalProcessed: Int,

    @JsonProperty("sucessos")
    val successCount: Int,

    @JsonProperty("erros")
    val errorCount: Int,

    @JsonProperty("projetosCriados")
    val createdProjects: List<ProjectCreateResponse>
)

fun BatchImportResult.toBatchCreateResponse() = ProjectBatchCreateResponse(
    totalProcessed = this.totalProcessed,
    successCount = this.successCount,
    errorCount = this.errorCount,
    createdProjects = this.savedProjects.map { it.toResponse() }
)
