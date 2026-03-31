package projects.core.respository

import projects.core.model.Files

interface FilesRepository {
    fun saveAll(t: List<Files>): List<Files>
    fun findAllByItemId(itemId: String): List<Files>
    fun findById(id: String): Files?
    fun deleteById(id: String)
    fun deleteAllByItemId(itemId: String)
}