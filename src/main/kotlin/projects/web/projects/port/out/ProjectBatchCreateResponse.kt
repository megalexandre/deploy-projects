package projects.web.projects.port.out

import com.fasterxml.jackson.annotation.JsonProperty

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

fun projects.core.usecase.project.BatchImportResult.toBatchCreateResponse() = ProjectBatchCreateResponse(
    totalProcessed = this.totalProcessed,
    successCount = this.successCount,
    errorCount = this.errorCount,
    createdProjects = this.savedProjects.map { it.toResponse() }
)
