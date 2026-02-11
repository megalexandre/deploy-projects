package projects.web.auth.port.out

import projects.core.model.User

class UserCreateResponse (
    val id: String,
)

fun User.toResponse() = UserCreateResponse(
    id = this.id,
)
