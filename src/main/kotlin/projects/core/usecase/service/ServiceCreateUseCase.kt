package projects.core.usecase.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import projects.core.model.Service as ServiceModel
import projects.core.respository.AddressRepository
import projects.core.respository.ServiceRepository

@Service
class ServiceCreateUseCase(
    private val serviceRepository: ServiceRepository,
    private val addressRepository: AddressRepository
) {

    private val logger = LoggerFactory.getLogger(ServiceCreateUseCase::class.java)

    fun execute(service: ServiceModel): ServiceModel {
        val constructionAddr = service.constructionAddress?.let { addressRepository.save(it) }
        val generatingAddr   = service.generatingAddress?.let   { addressRepository.save(it) }

        return serviceRepository.save(
            service.copy(
                constructionAddress = constructionAddr,
                generatingAddress   = generatingAddr
            )
        ).also {
            logger.info("Service created successfully with id: {}", it.id)
        }
    }
}
