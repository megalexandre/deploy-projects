package projects.core.usecase.files

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import projects.commons.Id
import projects.core.model.Files
import projects.core.respository.FilesRepository
import projects.infra.service.S3Service
import java.time.Instant

@Service
class FilesCreateUseCase(
    private val filesRepository: FilesRepository,
    private val s3Service: S3Service,
) {

    @Transactional
    fun execute(itemId: String, files: List<MultipartFile>): List<Files> {

        val itens = files.map { file ->
            val fileId = Id.random()
            val fileName = file.originalFilename ?: "file_${System.currentTimeMillis()}"

            val s3Key = "items/${itemId}/${fileId}_${fileName}"
            val urlS3 = s3Service.uploadFile(file, s3Key)

            Files(
                id = fileId,
                itemId = itemId,
                fileName = fileName,
                urlS3 = urlS3,
                size = file.size,
                createdAt = Instant.now(),
            )
        }

        return filesRepository.saveAll(itens)
    }

}