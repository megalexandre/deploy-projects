package projects.core.usecase.service

import org.springframework.stereotype.Service
import projects.core.model.ServiceDocument
import projects.core.respository.ServiceDocumentRepository

@Service
class ServiceDocumentFindAllUseCase(
    private val repository: ServiceDocumentRepository,
) {
    fun execute(serviceId: String): List<ServiceDocument> =
        repository.findAllByServiceId(serviceId)
}
