package projects.web.projects

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import projects.core.usecase.project.ProjectCreateUseCase
import projects.core.usecase.project.ProjectFindAllUseCase
import projects.core.usecase.project.ProjectFindByIdUseCase
import projects.infra.extensions.toResponseEntity
import projects.web.projects.port.`in`.ProjectCreateRequest
import projects.web.projects.port.`in`.ProjectUpdateRequest
import projects.web.projects.port.out.*

@RestController
@RequestMapping("/projects")
class ProjectController(
    private val create: ProjectCreateUseCase,
    private val findById: ProjectFindByIdUseCase,
    private val findAll: ProjectFindAllUseCase
    ) {

    private val logger = LoggerFactory.getLogger(ProjectController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody projectCreateRequest: ProjectCreateRequest): ProjectCreateResponse =
        create.execute(projectCreateRequest.toDomain()).toResponse().also{
            logger.info("Project created successfully with id: {}", it.id)
        }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@Valid @RequestBody projectUpdateRequest: ProjectUpdateRequest): ProjectCreateResponse =
        create.execute(projectUpdateRequest.toDomain()).toResponse().also {
            logger.info("Project updated successfully with id: {}", it.id)
        }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<ProjectFindByIdResponse> =
        findById.execute(id)?.toFindByIdResponse().toResponseEntity().also {
            if (it.statusCode == HttpStatus.OK) {
                logger.info("Project found with id: {}", id)
            } else {
                logger.warn("Project not found with id: {}", id)
            }
        }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<ProjectPaginateResponse> =
        findAll.execute().map { it.toPaginateResponse() }.also {
            logger.info("Fetching all projects, Found {} projects", it.size)
        }

}