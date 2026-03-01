package projects.web.address.port.out

import projects.core.model.Address

class AddressCreateResponse(
    val id: String,
    val link: String,
    val name: String,
    val place: String,
)

fun Address.toResponse() = AddressCreateResponse(
    id = this.id,
    link = this.link,
    name = this.name,
    place = this.place
)
