package projects.core.respository

interface CustomRepository<T> {
    fun save(t: T): T
    fun delete(id: String)
    fun findAll(): List<T>
    fun findById(id: String): T?
}