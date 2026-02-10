package projects.web.projects

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import projects.core.usecase.project.ProjectCreateUseCase
import projects.core.usecase.project.ProjectFindAllUseCase
import projects.core.usecase.project.ProjectFindByIdUseCase
import projects.web.projects.port.`in`.ProjectCreateRequest
import projects.web.projects.port.`in`.ProjectUpdateRequest
import projects.web.projects.port.out.ProjectCreateResponse
import projects.web.projects.port.out.ProjectFindByIdResponse
import projects.web.projects.port.out.ProjectPaginateResponse
import projects.web.projects.port.out.toFindByIdResponse
import projects.web.projects.port.out.toPaginateResponse
import projects.web.projects.port.out.toResponse
import java.util.Optional

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
    fun getById(@PathVariable id: String): ResponseEntity<ProjectFindByIdResponse> =
        ResponseEntity.of(Optional.ofNullable(findById.execute(id)?.toFindByIdResponse()))

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<ProjectPaginateResponse> =
        findAll.execute().map { it.toPaginateResponse() }

}