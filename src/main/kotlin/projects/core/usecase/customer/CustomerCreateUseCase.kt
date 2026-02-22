package projects.core.usecase.customer

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import projects.core.model.Customer
import projects.core.respository.CustomerRepository

@Service
class CustomerCreateUseCase(
    private val repository: CustomerRepository,
) {

    private val logger = LoggerFactory.getLogger(CustomerCreateUseCase::class.java)

    fun execute(customer: Customer): Customer =
        repository.save(customer)

}