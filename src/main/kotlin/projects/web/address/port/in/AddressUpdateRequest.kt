package projects.web.address.port.`in`

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import projects.core.model.Address

class AddressUpdateRequest(

    @field:JsonProperty("id")
    @field:NotBlank(message = "{project.id.notBlank}")
    @field:Size(max = 250, message = "{project.clientId.size}")
    @field:Pattern(regexp = "^[a-f0-9-]{36}$", flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{uuid.invalid}")
    val id: String?,

    place: String?,
    link: String?,
    cep: String?,
    number: String?,
    address: String?,
    complement: String? = null,
    neighborhood: String?,
    city: String?,
    state: String?

) : AddressCreateRequest(
    place = place,
    link = link,
    cep = cep,
    number = number,
    address = address,
    complement = complement,
    neighborhood = neighborhood,
    city = city,
    state = state
) {
    override fun toDomain(): Address {
        return super.toDomain().copy(
            id = this.id!!
        )
    }
}


