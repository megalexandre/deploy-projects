package projects.web.projects

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize
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

    @PostMapping
    @PostAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody projectCreateRequest: ProjectCreateRequest): ProjectCreateResponse =
        create.execute(projectCreateRequest.toDomain()).toResponse()

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@Valid @RequestBody projectUpdateRequest: ProjectUpdateRequest): ProjectCreateResponse =
        create.execute(projectUpdateRequest.toDomain()).toResponse()

    @GetMapping("/{id}")
    @PostAuthorize("hasRole('ADMIN')")
    fun getById(@PathVariable id: String): ResponseEntity<ProjectFindByIdResponse> =
        findById.execute(id)?.toFindByIdResponse().toResponseEntity()

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<ProjectPaginateResponse> =
        findAll.execute().map { it.toPaginateResponse() }

}