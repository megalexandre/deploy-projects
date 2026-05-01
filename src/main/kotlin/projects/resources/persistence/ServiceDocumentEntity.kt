package projects.resources.persistence

import jakarta.persistence.*
import projects.core.model.ServiceDocument
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "service_documents")
data class ServiceDocumentEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: UUID,

    @Column(name = "service_id", nullable = false)
    var serviceId: UUID,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "type", nullable = false)
    var type: String,

    @Column(name = "url_s3")
    var urlS3: String?,

    @Column(name = "size", nullable = false)
    var size: Long,

    @Column(name = "upload_date", nullable = false, updatable = false)
    var uploadDate: Instant,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant,
) {
    @PreUpdate
    fun preUpdate() {
        updatedAt = Instant.now()
    }

    fun toDomain() = ServiceDocument(
        id = id.toString(),
        serviceId = serviceId.toString(),
        name = name,
        type = type,
        urlS3 = urlS3,
        size = size,
        uploadDate = uploadDate,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun from(domain: ServiceDocument) = ServiceDocumentEntity(
            id = UUID.fromString(domain.id),
            serviceId = UUID.fromString(domain.serviceId),
            name = domain.name,
            type = domain.type,
            urlS3 = domain.urlS3,
            size = domain.size,
            uploadDate = domain.uploadDate,
            createdAt = domain.createdAt ?: Instant.now(),
            updatedAt = domain.updatedAt ?: Instant.now()
        )
    }
}
