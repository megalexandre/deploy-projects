package projects.resources

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import projects.core.model.User
import projects.core.respository.UserRepository
import projects.resources.persistence.UserEntity
import java.util.UUID

@Repository
class UsersRepositoryImpl(
    private val jpa: SpringDataUsersRepository
): UserRepository {

    override fun save(user: User): User  =
         jpa.save(UserEntity.from(user)).toDomain()

    override fun delete(id: String) {
        val key = UUID.fromString(id)

        if (jpa.existsById(key)) {
            jpa.deleteById(key)
        }
    }

    override fun findAll(): List<User> =
        jpa.findAll(Sort.by(Sort.Direction.ASC, "id")).map { it.toDomain() }

    override fun findById(id: String): User? =
        jpa.findById(UUID.fromString(id) ).orElse(null)?.toDomain()

}

@Repository
interface SpringDataUsersRepository : JpaRepository<UserEntity, UUID>
