package projects.web.auth

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import projects.core.usecase.auth.SignInUseCase
import projects.core.usecase.auth.UserCreateUseCase
import projects.web.auth.port.`in`.LoginRequest
import projects.web.auth.port.`in`.UserCreateRequest
import projects.web.auth.port.out.LoginResponse
import projects.web.auth.port.out.UserCreateResponse
import projects.web.auth.port.out.toResponse

@RestController
@RequestMapping("/auth")
class AuthController(
    private val create: UserCreateUseCase,
    private val singIn: SignInUseCase
    ) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@Valid @RequestBody userCreateRequest: UserCreateRequest): UserCreateResponse =
        create.execute(userCreateRequest.toDomain()).toResponse()

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(@Valid @RequestBody loginRequest: LoginRequest): LoginResponse {
        val token = singIn.execute(loginRequest.email, loginRequest.password)
        return LoginResponse(token = token)
    }

}