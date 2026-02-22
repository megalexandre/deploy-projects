package projects.core.usecase.customer

import org.springframework.stereotype.Service
import projects.core.model.Customer
import projects.core.respository.CustomerRepository

@Service
class CustomerFindAllUseCase(
    private val customerRepository: CustomerRepository
) {
    fun execute(): List<Customer> = customerRepository.findAll()
}
