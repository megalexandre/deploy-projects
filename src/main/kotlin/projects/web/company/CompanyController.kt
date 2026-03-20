package projects.web.company

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import projects.core.usecase.company.CompanyCreateUseCase
import projects.core.usecase.company.CompanyDeleteUseCase
import projects.core.usecase.company.CompanyFindAllUseCase
import projects.core.usecase.company.CompanyFindByIdUseCase
import projects.infra.extensions.toResponseEntity
import projects.web.company.port.`in`.CompanyCreateRequest
import projects.web.company.port.`in`.CompanyUpdateRequest
import projects.web.company.port.out.CompanyCreateResponse
import projects.web.company.port.out.toResponse

@RestController
@RequestMapping("/company")
class CompanyController(
    private val create: CompanyCreateUseCase,
    private val findAll: CompanyFindAllUseCase,
    private val findById: CompanyFindByIdUseCase,
    private val delete: CompanyDeleteUseCase
) {

    private val logger = LoggerFactory.getLogger(CompanyController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CompanyCreateRequest): CompanyCreateResponse =
        create.execute(request.toDomain()).toResponse().also {
            logger.info("company created successfully with id: {}", it.id)
        }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<CompanyCreateResponse> =
        findById.execute(id)?.toResponse().toResponseEntity().also {
            if (it.statusCode == HttpStatus.OK) {
                logger.info("Company found with id: {}", id)
            } else {
                logger.warn("Company not found with id: {}", id)
            }
        }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<CompanyCreateResponse> =
        findAll.execute().map { it.toResponse() }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: String){
        delete.execute(id)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@Valid @RequestBody request: CompanyUpdateRequest): CompanyCreateResponse =
        create.execute(request.toDomain()).toResponse().also {
            logger.info("company updated successfully with id: {}", it.id)
        }


}
