package projects.core.usecase.customer

import org.springframework.stereotype.Service
import projects.core.respository.CustomerRepository

@Service
class CustomerDeleteUseCase(
    private val customerRepository: CustomerRepository
) {
    fun execute(id: String) = customerRepository.delete(id)
}
