package projects.web

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import projects.core.usecase.project.ProjectCreateUseCase
import projects.core.usecase.project.ProjectFindAllUseCase
import projects.core.usecase.project.ProjectFindByIdUseCase
import projects.web.port.`in`.ProjectCreateRequest
import projects.web.port.`in`.ProjectUpdateRequest
import projects.web.port.out.*

@RestController
@RequestMapping("/projects")
class ProjectController(
    private val create: ProjectCreateUseCase,
    private val findById: ProjectFindByIdUseCase,
    private val findAll: ProjectFindAllUseCase

    ) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody projectCreateRequest: ProjectCreateRequest): ProjectCreateResponse =
        create.execute(projectCreateRequest.toDomain()).toResponse()

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@Valid @RequestBody projectUpdateRequest: ProjectUpdateRequest): ProjectCreateResponse =
        create.execute(projectUpdateRequest.toDomain()).toResponse()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable id: String): ProjectFindByIdResponse =
        findById.execute(id)?.toFindByIdResponse()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<ProjectPaginateResponse> =
        findAll.execute().map { it.toPaginateResponse() }

}
