package projects.web.customer.port.`in`

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import projects.core.model.Customer

class CustomerUpdateRequest(

    @field:JsonProperty("id")
    @field:NotNull(message = "{customer.id.notBlank}")
    @field:NotBlank(message = "{customer.id.notBlank}")
    @field:Size(max = 250, message = "{customer.id.size}")
    @field:Pattern(regexp = "^[a-f0-9-]{36}$", flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{uuid.invalid}")
    val id: String?,

    @field:JsonProperty("nome")
    @field:NotNull(message = "{customer.name.notBlank}")
    @field:NotBlank(message = "{customer.name.notBlank}")
    @field:Size(min = 2, max = 250, message = "{customer.name.size}")
    val name: String?,

    @field:JsonProperty("cpfCnpj")
    @field:NotNull(message = "{customer.taxId.notBlank}")
    @field:NotBlank(message = "{customer.taxId.notBlank}")
    @field:Size(min = 11, max = 14, message = "{customer.taxId.size}")
    @field:Pattern(regexp = "^[0-9]{11}|[0-9]{14}$", message = "{customer.taxId.invalid}")
    val taxId: String?,

    @field:JsonProperty("telefone")
    @field:NotNull(message = "{customer.phone.notBlank}")
    @field:NotBlank(message = "{customer.phone.notBlank}")
    @field:Size(min = 10, max = 15, message = "{customer.phone.size}")
    val phone: String?,

    @field:JsonProperty("email")
    @field:NotNull(message = "{customer.email.notBlank}")
    @field:NotBlank(message = "{customer.email.notBlank}")
    @field:Email(message = "{customer.email.invalid}")
    @field:Size(min = 5, max = 250, message = "{customer.email.size}")
    val email: String?
) {
    fun toDomain() = Customer(
        id = id!!,
        name = name!!,
        taxId = taxId!!,
        phone = phone!!,
        email = email!!
    )
}
