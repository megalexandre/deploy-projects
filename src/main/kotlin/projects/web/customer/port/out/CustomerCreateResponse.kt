package projects.web.customer.port.out

import projects.core.model.Customer

class CustomerCreateResponse(
    val id: String,
    val addressId: String?,
    val name: String,
    val taxId: String,
    val phone: String,
    val email: String
)

fun Customer.toResponse() = CustomerCreateResponse(
    id = this.id,
    addressId = this.addressId,
    name = this.name,
    taxId = this.taxId,
    phone = this.phone,
    email = this.email
)
