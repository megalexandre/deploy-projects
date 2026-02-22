package projects.core.usecase.customer

import org.springframework.stereotype.Service
import projects.core.model.Customer
import projects.core.respository.CustomerRepository

@Service
class CustomerUpdateUseCase(
    private val customerRepository: CustomerRepository
) {
    fun execute(customer: Customer): Customer = customerRepository.save(customer)
}
