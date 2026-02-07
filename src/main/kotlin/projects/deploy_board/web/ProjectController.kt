package projects.deploy_board.web

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import projects.deploy_board.web.port.`in`.ProjectCreateRequest
import projects.deploy_board.web.port.out.ProjectCreateResponse
import projects.deploy_board.web.port.out.toResponse
import projects.deploy_board.core.usecase.ProjectCreateUseCase

@RestController
@RequestMapping("/projects")
class ProjectController(
    private val projectCreateUseCase: ProjectCreateUseCase
) {

    @PostMapping
    fun create(@Valid @RequestBody projectCreateRequest: ProjectCreateRequest): ProjectCreateResponse =
        projectCreateUseCase.execute(projectCreateRequest.toDomain()).toResponse()

}
