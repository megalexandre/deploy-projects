package projects.web.user

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import projects.core.usecase.users.UserCreateUseCase
import projects.web.user.port.`in`.UserCreateRequest
import projects.web.user.port.out.UserCreateResponse
import projects.web.user.port.out.toResponse

@RestController
@RequestMapping("/users")
class UserController(
    private val create: UserCreateUseCase,
    ) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody userCreateRequest: UserCreateRequest): UserCreateResponse =
        create.execute(userCreateRequest.toDomain()).toResponse()

}