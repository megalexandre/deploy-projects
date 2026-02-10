package projects.web.user.port.out

import projects.core.model.User

class UserCreateResponse (
    val id: String,
)

fun User.toResponse() = UserCreateResponse(
    id = this.id,
)

fun List<User>.toResponse(): List<UserCreateResponse> = this.map { it.toResponse() }