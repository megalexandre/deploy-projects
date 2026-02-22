package projects.web.customer

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import projects.core.usecase.customer.*
import projects.infra.extensions.toResponseEntity
import projects.web.customer.port.`in`.CustomerCreateRequest
import projects.web.customer.port.`in`.CustomerUpdateRequest
import projects.web.customer.port.out.*

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val create: CustomerCreateUseCase,
    private val update: CustomerUpdateUseCase,
    private val findById: CustomerFindByIdUseCase,
    private val findAll: CustomerFindAllUseCase,
    private val delete: CustomerDeleteUseCase
) {

    private val logger = LoggerFactory.getLogger(CustomerController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody customerCreateRequest: CustomerCreateRequest): CustomerCreateResponse =
        create.execute(customerCreateRequest.toDomain()).toResponse().also {
            logger.info("Customer created successfully with id: {}", it.id)
        }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@Valid @RequestBody customerUpdateRequest: CustomerUpdateRequest): CustomerCreateResponse =
        update.execute(customerUpdateRequest.toDomain()).toResponse().also {
            logger.info("Customer updated successfully with id: {}", it.id)
        }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<CustomerFindByIdResponse> =
        findById.execute(id)?.toFindByIdResponse().toResponseEntity().also {
            if (it.statusCode == HttpStatus.OK) {
                logger.info("Customer found with id: {}", id)
            } else {
                logger.warn("Customer not found with id: {}", id)
            }
        }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<CustomerPaginateResponse> =
        findAll.execute().map { it.toPaginateResponse() }.also {
            logger.info("Fetching all customers, Found {} customers", it.size)
        }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: String) {
        delete.execute(id)
        logger.info("Customer deleted with id: {}", id)
    }

}
