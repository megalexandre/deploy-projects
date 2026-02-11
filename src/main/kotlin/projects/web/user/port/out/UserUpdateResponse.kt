package projects.web.user.port.out

import projects.core.model.User

class UserUpdateResponse (
    val id: String,
)

fun User.toUpdateResponse() = UserUpdateResponse(
    id = this.id,
)
