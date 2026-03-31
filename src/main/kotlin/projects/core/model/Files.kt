package projects.core.model

import java.time.Instant

data class Files(
    val id: String,
    val itemId: String,
    val fileName: String,
    val urlS3: String,
    val size: Long,
    val createdAt: Instant?
)

