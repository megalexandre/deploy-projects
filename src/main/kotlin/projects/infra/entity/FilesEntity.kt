package projects.infra.entity

import jakarta.persistence.*
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "files")
data class FilesEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    val id: UUID,

    @Column(name = "item_id", nullable = false)
    val itemId: UUID,

    @Column(name = "filename", nullable = false)
    val filename: String,

    @Column(name = "url_s3", nullable = false)
    val urlS3: String,

    @Column(nullable = false)
    val size: Long,

    @Column(nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),

    @Column(nullable = false)
    var updatedAt: Instant = Instant.now(),
) {
    @PreUpdate
    fun onPreUpdate() {
        updatedAt = Instant.now()
    }
}
