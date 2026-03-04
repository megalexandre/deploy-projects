package projects.web.address.port.`in`

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
    val link: String?,

    @field:NotNull(message = "{address.cep.notBlank}")
    @field:NotBlank(message = "{address.cep.notBlank}")
    @field:Size(min = 8, max = 9, message = "{address.cep.size}")
    val cep: String?,

    @field:NotNull(message = "{address.number.notBlank}")
    @field:NotBlank(message = "{address.number.notBlank}")
    val number: String?,

    @field:NotNull(message = "{address.address.notBlank}")
    @field:NotBlank(message = "{address.address.notBlank}")
    @field:Size(min = 3, message = "{address.address.size}")
    val address: String?,

    val complement: String? = null,

    @field:NotNull(message = "{address.neighborhood.notBlank}")
    @field:NotBlank(message = "{address.neighborhood.notBlank}")
    @field:Size(min = 2, message = "{address.neighborhood.size}")
    val neighborhood: String?,

    @field:NotNull(message = "{address.city.notBlank}")
    @field:NotBlank(message = "{address.city.notBlank}")
    @field:Size(min = 2, message = "{address.city.size}")
    val city: String?,

    @field:NotNull(message = "{address.state.notBlank}")
    @field:NotBlank(message = "{address.state.notBlank}")
    @field:Size(min = 2, max = 2, message = "{address.state.size}")
    val state: String?
) {
    fun toDomain() = Address(
        id = Id.random(),
        name = name!!,
        place = place!!,
        link = link,
        cep = cep!!,
        number = number!!,
        address = address!!,
        complement = complement,
        neighborhood = neighborhood!!,
        city = city!!,
        state = state!!
    )
}
