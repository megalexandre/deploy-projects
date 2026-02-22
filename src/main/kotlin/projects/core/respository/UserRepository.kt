package projects.core.respository

import projects.core.model.User

interface UserRepository: CustomRepository<User> {
    fun findByEmail(email: String): User?
}