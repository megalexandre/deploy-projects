package projects.web.address.port.out

import projects.core.model.Address

class AddressCreateResponse(
    val id: String,
    val link: String?,
    val place: String,
    val cep: String,
    val number: String,
    val address: String,
    val complement: String?,
    val neighborhood: String,
    val city: String,
    val state: String
)

fun Address.toResponse() = AddressCreateResponse(
    id = this.id,
    link = this.link,
    place = this.place,
    cep = this.cep,
    number = this.number,
    address = this.address,
    complement = this.complement,
    neighborhood = this.neighborhood,
    city = this.city,
    state = this.state
)
