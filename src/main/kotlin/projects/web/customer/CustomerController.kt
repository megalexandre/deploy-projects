package projects.web.customer

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import projects.core.usecase.customer.CustomerCreateUseCase
import projects.web.customer.port.`in`.CustomerCreateRequest
import projects.web.customer.port.out.CustomerCreateResponse
import projects.web.customer.port.out.toResponse

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val create: CustomerCreateUseCase,
) {

    private val logger = LoggerFactory.getLogger(CustomerController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody customerCreateRequest: CustomerCreateRequest): CustomerCreateResponse =
        create.execute(customerCreateRequest.toDomain()).toResponse().also {
            logger.info("Customer created successfully with id: {}", it.id)
        }

}
