package projects.web.customer.port.out

import com.fasterxml.jackson.annotation.JsonProperty
import projects.core.model.Customer

class CustomerPaginateResponse(
    val id: String,

    @field:JsonProperty("nome")
    val name: String,

    @field:JsonProperty("cpfCnpj")
    val taxId: String,

    @field:JsonProperty("telefone")
    val phone: String,

    @field:JsonProperty("email")
    val email: String
)

fun Customer.toPaginateResponse() = CustomerPaginateResponse(
    id = this.id,
    name = this.name,
    taxId = this.taxId,
    phone = this.phone,
    email = this.email
)
