package projects.web.controller.dto

import projects.core.model.Files
import java.time.Instant

data class FilesResponse(
    val id: String,
    val fileName: String,
    val urlS3: String,
    val size: Long,
    val createdAt: Instant?
) {
    companion object {
        fun fromDomain(file: Files): FilesResponse {
            return FilesResponse(
                id = file.id,
                fileName = file.fileName,
                urlS3 = file.urlS3,
                size = file.size,
                createdAt = file.createdAt
            )
        }
    }
}

fun List<Files>.toResponse(): List<FilesResponse> {
    return this.map { FilesResponse.fromDomain(it) }
}
