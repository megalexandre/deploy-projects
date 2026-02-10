package projects.core.respository

import projects.core.model.User

interface UserRepository {
    fun save(user: User): User
    fun delete(id: String)
    fun findAll(): List<User>
    fun findById(id: String): User?
}