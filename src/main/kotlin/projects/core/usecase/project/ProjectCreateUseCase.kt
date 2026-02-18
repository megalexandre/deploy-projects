package projects.core.usecase.project

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import projects.core.model.Project
import projects.core.respository.ProjectRepository
import projects.infra.metrics.ProjectMetricsService

@Service
class ProjectCreateUseCase(
    private val projectRepository: ProjectRepository,
    private val metricsService: ProjectMetricsService
) {

    private val logger = LoggerFactory.getLogger(ProjectCreateUseCase::class.java)

    fun execute(project: Project): Project =

        projectRepository.save(project).also {
            metricsService.incrementProjectCreated()
        }

}