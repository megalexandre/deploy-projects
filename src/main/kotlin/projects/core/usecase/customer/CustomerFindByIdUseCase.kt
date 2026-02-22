package projects.core.usecase.customer

import org.springframework.stereotype.Service
import projects.core.model.Customer
import projects.core.respository.CustomerRepository

@Service
class CustomerFindByIdUseCase(
    private val customerRepository: CustomerRepository
) {
    fun execute(id: String): Customer? = customerRepository.findById(id)
}
