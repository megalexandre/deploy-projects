package projects.web.services

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import projects.core.usecase.service.ServiceCreateUseCase
import projects.core.usecase.service.ServiceFindAllUseCase
import projects.core.usecase.service.ServiceFindByIdUseCase
import projects.infra.extensions.toResponseEntity
import projects.web.services.port.`in`.ServiceCreateRequest
import projects.web.services.port.out.ServiceCreateResponse
import projects.web.services.port.out.ServiceFindByIdResponse
import projects.web.services.port.out.toCreateResponse
import projects.web.services.port.out.toFindByIdResponse

@RestController
@RequestMapping("/services")
class ServicesController(
    private val create: ServiceCreateUseCase,
    private val findAll: ServiceFindAllUseCase,
    private val findById: ServiceFindByIdUseCase,
) {

    private val logger = LoggerFactory.getLogger(ServicesController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: ServiceCreateRequest): ServiceCreateResponse =
        create.execute(request.toDomain()).toCreateResponse().also {
            logger.info("Service created successfully with id: {}", it.id)
        }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<ServiceFindByIdResponse> =
        findAll.execute().map { it.toFindByIdResponse() }.also {
            logger.info("Fetching all services, found {} services", it.size)
        }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<ServiceFindByIdResponse> =
        findById.execute(id)?.toFindByIdResponse().toResponseEntity().also {
            if (it.statusCode == HttpStatus.OK) {
                logger.info("Service found with id: {}", id)
            } else {
                logger.warn("Service not found with id: {}", id)
            }
        }
}
