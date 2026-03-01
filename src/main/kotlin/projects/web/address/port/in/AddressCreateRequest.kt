package projects.web.address.port.`in`

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import projects.commons.Id
import projects.core.model.Address

class AddressCreateRequest(

    @field:NotNull(message = "{customer.name.notBlank}")
    @field:NotBlank(message = "{customer.name.notBlank}")
    @field:Size(min = 2, message = "{customer.name.size}")
    val name: String?,

    @field:NotNull(message = "{customer.email.notBlank}")
    @field:NotBlank(message = "{customer.email.notBlank}")
    @field:Size(min = 5, message = "{customer.email.size}")
    val place: String?,

    @field:NotNull(message = "{customer.email.notBlank}")
    @field:NotBlank(message = "{customer.email.notBlank}")
    @field:Size(min = 5, message = "{customer.email.size}")
    val link: String?
) {
    fun toDomain() = Address(
        id = Id.random(),
        name = name!!,
        place = place!!,
        link = link!!
    )
}
