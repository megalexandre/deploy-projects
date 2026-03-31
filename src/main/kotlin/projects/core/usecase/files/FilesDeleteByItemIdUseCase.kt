package projects.core.usecase.files

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import projects.core.respository.FilesRepository
import projects.infra.service.S3Service

@Service
class FilesDeleteByItemIdUseCase(
    private val filesRepository: FilesRepository,
    private val s3Service: S3Service
) {

    @Transactional
    fun execute(itemId: String): Int {
        // Buscar todos os arquivos do item
        val files = filesRepository.findAllByItemId(itemId)

        if (files.isEmpty()) {
            return 0
        }

        // Deletar cada arquivo do S3
        files.forEach { file ->
            val s3Key = extractS3KeyFromUrl(file.urlS3)
            s3Service.deleteFile(s3Key)
        }

        // Deletar todos os registros do banco de dados
        filesRepository.deleteAllByItemId(itemId)

        return files.size
    }

    private fun extractS3KeyFromUrl(url: String): String {
        // URL format: https://bucket-name.s3.amazonaws.com/key
        return url.substringAfter(".com/")
    }

}

