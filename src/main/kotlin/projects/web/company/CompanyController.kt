package projects.web.company

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import projects.core.usecase.company.CompanyCreateUseCase
import projects.web.company.port.`in`.CompanyCreateRequest
import projects.web.company.port.`in`.CompanyUpdateRequest
import projects.web.company.port.out.CompanyCreateResponse
import projects.web.company.port.out.toResponse

@RestController
@RequestMapping("/company")
class CompanyController(
    private val create: CompanyCreateUseCase,
    //#private val update: CustomerUpdateUseCase,
    //#private val findById: CustomerFindByIdUseCase,
    //#private val findAll: CustomerFindAllUseCase,
    //p#rivate val delete: CustomerDeleteUseCase
) {

    private val logger = LoggerFactory.getLogger(CompanyController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CompanyCreateRequest): CompanyCreateResponse =
        create.execute(request.toDomain()).toResponse().also {
            logger.info("company created successfully with id: {}", it.id)
        }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@Valid @RequestBody request: CompanyUpdateRequest): CompanyCreateResponse =
        create.execute(request.toDomain()).toResponse().also {
            logger.info("company updated successfully with id: {}", it.id)
        }


}
