package projects.core.usecase.service

import org.springframework.stereotype.Service
import projects.core.model.Service as ServiceModel
import projects.core.respository.ServiceRepository

@Service
class ServiceFindAllUseCase(
    private val serviceRepository: ServiceRepository
) {
    fun execute(): List<ServiceModel> = serviceRepository.findAll()
}
