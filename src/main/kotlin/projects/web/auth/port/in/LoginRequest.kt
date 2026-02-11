package projects.web.auth.port.`in`

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank(message = "Email é obrigatório")
    @field:Email(message = "Email deve ser válido")
    val email: String,

    @field:NotBlank(message = "Password é obrigatório")
    val password: String
)