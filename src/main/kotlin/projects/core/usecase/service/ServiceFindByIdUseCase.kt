package projects.core.usecase.service

import org.springframework.stereotype.Service
import projects.core.model.Service as ServiceModel
import projects.core.respository.ServiceRepository

@Service
class ServiceFindByIdUseCase(
    private val serviceRepository: ServiceRepository
) {
    fun execute(id: String): ServiceModel? = serviceRepository.findById(id)
}
