package projects.resources

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import projects.core.model.Files
import projects.core.respository.FilesRepository
import projects.infra.entity.FilesEntity
import java.util.*

@Repository
class FilesRepositoryImpl(
    private val jpa: SpringDataFilesRepository
): FilesRepository {

    override fun saveAll(t: List<Files>): List<Files> {
        return jpa.saveAll(t.map { it.toEntity() }).map { it.toDomain() }
    }

    override fun findAllByItemId(itemId: String): List<Files> {
        val uuid = UUID.fromString(itemId)
        return jpa.findAllByItemId(uuid).map { it.toDomain() }
    }

    override fun findById(id: String): Files? {
        val uuid = UUID.fromString(id)
        return jpa.findById(uuid).map { it.toDomain() }.orElse(null)
    }

    override fun deleteById(id: String) {
        val uuid = UUID.fromString(id)
        jpa.deleteById(uuid)
    }

    override fun deleteAllByItemId(itemId: String) {
        val uuid = UUID.fromString(itemId)
        jpa.deleteAllByItemId(uuid)
    }

}

@Repository
interface SpringDataFilesRepository : JpaRepository<FilesEntity, UUID> {
    fun findAllByItemId(itemId: UUID): List<FilesEntity>
    fun deleteAllByItemId(itemId: UUID)
}

fun Files.toEntity(): FilesEntity {
    return FilesEntity(
        id = UUID.fromString(this.id),
        itemId = UUID.fromString(this.itemId),
        filename = this.fileName,
        urlS3 = this.urlS3,
        size = this.size,
        createdAt = this.createdAt ?: java.time.Instant.now(),
        updatedAt = java.time.Instant.now()
    )
}

fun FilesEntity.toDomain(): Files {
    return Files(
        id = this.id.toString(),
        itemId = this.itemId.toString(),
        fileName = this.filename,
        urlS3 = this.urlS3,
        size = this.size,
        createdAt = this.createdAt
    )
}

