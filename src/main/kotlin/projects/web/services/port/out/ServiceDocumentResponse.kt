package projects.web.services.port.out

import projects.core.model.ServiceDocument
import java.time.Instant

data class ServiceDocumentResponse(
    val id: String,
    val serviceId: String,
    val name: String,
    val type: String,
    val urlS3: String?,
    val size: Long,
    val uploadDate: Instant,
)

fun ServiceDocument.toResponse() = ServiceDocumentResponse(
    id = id,
    serviceId = serviceId,
    name = name,
    type = type,
    urlS3 = urlS3,
    size = size,
    uploadDate = uploadDate,
)

fun List<ServiceDocument>.toResponse() = map { it.toResponse() }
