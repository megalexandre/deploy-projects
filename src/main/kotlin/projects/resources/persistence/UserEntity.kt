package projects.resources.persistence

import jakarta.persistence.*
import projects.core.model.User
import java.time.Instant
import java.util.*

@Entity
@Table(name = "users")
data class UserEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: UUID,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "profile", nullable = false)
    var profile: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant
) {

    @PreUpdate
    fun preUpdate() {
        updatedAt = Instant.now()
    }

    fun toDomain(): User =
        User(
            id = id.toString(),
            name = name,
            email = email,
            password = password,
            profile = profile,
            createdAt = createdAt,
            updatedAt = updatedAt
        )

    companion object {
        fun from(domain: User): UserEntity =
            UserEntity(
                id = UUID.fromString( domain.id),
                name = domain.name,
                email = domain.email,
                password = domain.password,
                profile = domain.profile,
                createdAt = domain.createdAt ?: Instant.now(),
                updatedAt = domain.updatedAt ?: Instant.now()
            )
    }
}
