package projects.web

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import projects.web.port.`in`.ProjectCreateRequest
import projects.web.port.out.ProjectCreateResponse
import projects.web.port.out.toResponse
import projects.core.usecase.ProjectCreateUseCase

@RestController
@RequestMapping("/projects")
class ProjectController(
    private val projectCreateUseCase: ProjectCreateUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody projectCreateRequest: ProjectCreateRequest): ProjectCreateResponse =
        projectCreateUseCase.execute(projectCreateRequest.toDomain()).toResponse()

}
