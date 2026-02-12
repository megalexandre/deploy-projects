package projects.web.auth.port.out

import projects.core.model.User

class UserCreateResponse (
    val id: String,
    val name: String,
    val email: String,
    val profile: String,
)

fun User.toResponse() = UserCreateResponse(
    id = this.id,
    name = this.name,
    email = this.email,
    profile = this.profile
)
