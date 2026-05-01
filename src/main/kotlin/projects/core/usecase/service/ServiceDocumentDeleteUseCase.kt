package projects.core.usecase.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import projects.core.exceptions.ResourceNotFoundException
import projects.core.respository.ServiceDocumentRepository
import projects.infra.service.S3Service

@Service
class ServiceDocumentDeleteUseCase(
    private val repository: ServiceDocumentRepository,
    private val s3Service: S3Service,
) {

    @Transactional
    fun execute(id: String) {
        val doc = repository.findById(id)
            ?: throw ResourceNotFoundException("Documento não encontrado: $id")

        doc.urlS3?.let { url ->
            val s3Key = url.substringAfter(".amazonaws.com/").substringAfter(".localstack.cloud/")
            s3Service.deleteFile(s3Key)
        }

        repository.deleteById(id)
    }
}
