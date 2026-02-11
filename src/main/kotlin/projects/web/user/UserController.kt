package projects.web.user

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import projects.core.usecase.users.UserCreateUseCase
import projects.core.usecase.users.UserFindAllUseCase
import projects.core.usecase.users.UserFindByIdUseCase
import projects.core.usecase.users.UserUpdateUseCase
import projects.infra.extensions.toResponseEntity
import projects.web.user.port.`in`.UserCreateRequest
import projects.web.user.port.`in`.UserUpdateRequest
import projects.web.user.port.out.*

@RestController
@RequestMapping("/users")
class UserController(
    private val create: UserCreateUseCase,
    private val update: UserUpdateUseCase,
    private val findAll: UserFindAllUseCase,
    private val findById: UserFindByIdUseCase,
    ) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody userCreateRequest: UserCreateRequest): UserCreateResponse =
        create.execute(userCreateRequest.toDomain()).toResponse()

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@Valid @RequestBody request: UserUpdateRequest): UserUpdateResponse =
        update.execute(request.toDomain()).toUpdateResponse()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<UserFindByIdResponse> =
        findById.execute(id)?.toFindByIdResponse().toResponseEntity()

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<UserFindAllResponse> =
        findAll.execute().toFindAllResponse()

}