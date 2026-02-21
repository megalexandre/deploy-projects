package projects.core.usecase.project

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import projects.core.model.Project
import projects.core.respository.ProjectRepository
import projects.infra.metrics.ProjectMetricsService

@Service
class ProjectBatchCreateUseCase(
    private val projectRepository: ProjectRepository,
    private val metricsService: ProjectMetricsService
) {

    private val logger = LoggerFactory.getLogger(ProjectBatchCreateUseCase::class.java)

    @Transactional
    fun execute(projects: List<Project>): BatchImportResult {
        val savedProjects = mutableListOf<Project>()
        val errors = mutableListOf<ProjectImportError>()

        projects.forEachIndexed { index, project ->
            try {
                val saved = projectRepository.save(project)
                savedProjects.add(saved)
                metricsService.incrementProjectCreated()
                logger.debug("Project imported successfully at index {}: {}", index, saved.id)
            } catch (e: Exception) {
                logger.error("Error importing project at index {}: {}", index, e.message, e)
                errors.add(ProjectImportError(
                    index = index,
                    clientId = project.clientId,
                    error = e.message ?: "Unknown error"
                ))
            }
        }

        logger.info("Batch import completed: {} successes, {} errors", savedProjects.size, errors.size)

        return BatchImportResult(
            totalProcessed = projects.size,
            successCount = savedProjects.size,
            errorCount = errors.size,
            savedProjects = savedProjects,
            errors = errors
        )
    }
}

/*@TODO mover isso pra uma classe propia*/
data class BatchImportResult(
    val totalProcessed: Int,
    val successCount: Int,
    val errorCount: Int,
    val savedProjects: List<Project>,
    val errors: List<ProjectImportError>
)

data class ProjectImportError(
    val index: Int,
    val clientId: String,
    val error: String
)
