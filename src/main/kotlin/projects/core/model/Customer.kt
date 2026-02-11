package projects.core.model

import java.time.Instant

data class User (
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val profile: String,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null
)