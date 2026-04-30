package projects.web.services.port.`in`

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import projects.commons.Id
import projects.core.model.Address

class ServiceAddressRequest(

    @field:NotNull
    @field:NotBlank
    @field:Size(min = 8, max = 9)
    val zipCode: String?,

    @field:NotNull
    @field:NotBlank
    @field:Size(min = 3)
    val street: String?,

    @field:NotNull
    @field:NotBlank
    val number: String?,

    val complement: String? = null,

    @field:NotNull
    @field:NotBlank
    @field:Size(min = 2)
    val neighborhood: String?,

    @field:NotNull
    @field:NotBlank
    @field:Size(min = 2)
    val city: String?,

    @field:NotNull
    @field:NotBlank
    @field:Size(min = 2, max = 2)
    val state: String?,

    val link: String? = null,
) {
    fun toDomain() = Address(
        id = Id.random(),
        place = street!!,
        cep = zipCode!!,
        address = street,
        number = number!!,
        complement = complement,
        neighborhood = neighborhood!!,
        city = city!!,
        state = state!!,
        link = link
    )
}
