package projects.web.address

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import projects.core.usecase.address.AddressCreateUseCase
import projects.core.usecase.address.AddressFindAllUseCase
import projects.core.usecase.address.AddressFindByIdUseCase
import projects.web.address.port.`in`.AddressCreateRequest
import projects.web.address.port.`in`.AddressUpdateRequest
import projects.web.address.port.out.AddressCreateResponse
import projects.web.address.port.out.toResponse


@RestController
@RequestMapping("/address")
class AddressController(
    private val create: AddressCreateUseCase,
    private val findAll: AddressFindAllUseCase,
    private val findById: AddressFindByIdUseCase,

) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody addressCreateRequest: AddressCreateRequest): AddressCreateResponse =
        create.execute(addressCreateRequest.toDomain()).toResponse()

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@Valid @RequestBody addressCreateRequest: AddressUpdateRequest): AddressCreateResponse =
        create.execute(addressCreateRequest.toDomain()).toResponse()

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<AddressCreateResponse> =
        findAll.execute().map { it.toResponse() }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): AddressCreateResponse? =
        findById.execute(id)?.toResponse()
}
