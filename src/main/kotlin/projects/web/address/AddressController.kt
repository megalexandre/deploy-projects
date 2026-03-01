package projects.web.address

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import projects.core.usecase.address.AddressCreateUseCase
import projects.web.address.port.`in`.AddressCreateRequest
import projects.web.address.port.out.AddressCreateResponse
import projects.web.address.port.out.toResponse


@RestController
@RequestMapping("/address")
class AddressController(
    val create: AddressCreateUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody addressCreateRequest: AddressCreateRequest): AddressCreateResponse =
        create.execute(addressCreateRequest.toDomain()).toResponse()
}