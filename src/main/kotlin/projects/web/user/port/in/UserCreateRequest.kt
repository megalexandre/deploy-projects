package projects.web.user.port.`in`

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import projects.commons.Id
import projects.core.model.User

class UserCreateRequest (

    @field:JsonProperty("nome")
    @field:NotNull(message = "{user.name.notBlank}")
    @field:NotBlank(message = "{user.name.notBlank}")
    @field:Size(min = 2, max = 250, message = "{user.name.size}")
    val name: String?,

    @field:Email(message = "{user.email.invalid}")
    @field:JsonProperty("email")
    @field:NotNull(message = "{user.email.notBlank}")
    @field:NotBlank(message = "{user.email.notBlank}")
    @field:Size(min = 2, max = 250, message = "{user.email.size}")
    val email: String?,

    @field:JsonProperty("perfil")
    @field:NotNull(message = "{user.profile.notBlank}")
    @field:NotBlank(message = "{user.profile.notBlank}")
    @field:Size(min = 2, max = 250, message = "{user.profile.size}")
    val profile: String?,
) {
    fun toDomain() = User(
        id = Id.random(),
        name = name!!.trim(),
        email = email!!.trim(),
        profile = profile!!.trim()
    )
}