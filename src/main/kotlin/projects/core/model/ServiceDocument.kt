package projects.core.model

import java.time.Instant

data class ServiceDocument(
    val id: String,
    val serviceId: String,
    val name: String,
    val type: String,
    val urlS3: String?,
    val size: Long,
    val uploadDate: Instant,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null
)
