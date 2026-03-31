package projects.core.usecase.files

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import projects.core.respository.FilesRepository
import projects.infra.service.S3Service

@Service
class FilesDownloadUseCase(
    private val filesRepository: FilesRepository,
    private val s3Service: S3Service
) {

    data class FileDownload(
        val fileName: String,
        val content: ByteArray,
        val contentType: String = "application/octet-stream"
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as FileDownload

            if (fileName != other.fileName) return false
            if (!content.contentEquals(other.content)) return false
            if (contentType != other.contentType) return false

            return true
        }

        override fun hashCode(): Int {
            var result = fileName.hashCode()
            result = 31 * result + content.contentHashCode()
            result = 31 * result + contentType.hashCode()
            return result
        }
    }

    @Transactional(readOnly = true)
    fun execute(fileId: String): FileDownload? {
        val file = filesRepository.findById(fileId) ?: return null

        // Extrair a key do S3 da URL
        val s3Key = extractS3KeyFromUrl(file.urlS3)
        val content = s3Service.downloadFile(s3Key)

        return FileDownload(
            fileName = file.fileName,
            content = content
        )
    }

    private fun extractS3KeyFromUrl(url: String): String {
        // URL format: https://bucket-name.s3.amazonaws.com/key
        // Extrai a parte após o domínio
        return url.substringAfter(".com/")
    }

}

