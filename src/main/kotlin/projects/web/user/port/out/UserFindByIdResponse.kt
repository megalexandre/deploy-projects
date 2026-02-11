package projects.web.user.port.out

import projects.core.model.User
import java.time.Instant

class UserFindByIdResponse (
    val id: String,
    val name: String,
    val email: String,
    val profile: String,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null
)

fun User.toFindByIdResponse(): UserFindByIdResponse = UserFindByIdResponse(
    id = this.id,
    name = this.name,
    email = this.email,
    profile = this.profile,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)
