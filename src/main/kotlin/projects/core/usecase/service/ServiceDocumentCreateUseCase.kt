package projects.core.usecase.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import projects.commons.Id
import projects.core.model.ServiceDocument
import projects.core.respository.ServiceDocumentRepository
import projects.infra.service.S3Service
import java.time.Instant

@Service
class ServiceDocumentCreateUseCase(
    private val repository: ServiceDocumentRepository,
    private val s3Service: S3Service,
) {

    @Transactional
    fun execute(serviceId: String, type: String, file: MultipartFile): ServiceDocument {
        val docId = Id.random()
        val fileName = file.originalFilename ?: "document_${System.currentTimeMillis()}"
        val s3Key = "services/$serviceId/$docId/$fileName"
        val urlS3 = s3Service.uploadFile(file, s3Key)

        val doc = ServiceDocument(
            id = docId,
            serviceId = serviceId,
            name = fileName,
            type = type,
            urlS3 = urlS3,
            size = file.size,
            uploadDate = Instant.now(),
        )

        return repository.save(doc)
    }
}
