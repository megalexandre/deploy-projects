package projects.web.user.port.out

import projects.core.model.User

class UserFindAllResponse (
    val id: String,
    val name: String,
    val email: String,
    val profile: String,
)

fun User.toUserFindAllResponse(): UserFindAllResponse= UserFindAllResponse(
    id = this.id,
    name = this.name,
    email = this.email,
    profile = this.profile
)

fun List<User>.toFindAllResponse(): List<UserFindAllResponse> = this.map { it.toUserFindAllResponse() }