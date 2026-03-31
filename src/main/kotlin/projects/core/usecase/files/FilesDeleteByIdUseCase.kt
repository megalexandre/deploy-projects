package projects.core.usecase.files

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import projects.core.respository.FilesRepository
import projects.infra.service.S3Service

@Service
class FilesDeleteByIdUseCase(
    private val filesRepository: FilesRepository,
    private val s3Service: S3Service
) {

    @Transactional
    fun execute(fileId: String): Boolean {
        val file = filesRepository.findById(fileId) ?: return false

        // Extrair a key do S3 da URL e deletar do S3
        val s3Key = extractS3KeyFromUrl(file.urlS3)
        s3Service.deleteFile(s3Key)

        // Deletar do banco de dados
        filesRepository.deleteById(fileId)

        return true
    }

    private fun extractS3KeyFromUrl(url: String): String {
        // URL format: https://bucket-name.s3.amazonaws.com/key
        return url.substringAfter(".com/")
    }

}

