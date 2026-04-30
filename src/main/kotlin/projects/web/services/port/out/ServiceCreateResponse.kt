package projects.web.services.port.out

import projects.core.model.Service

class ServiceCreateResponse(val id: String)

fun Service.toCreateResponse() = ServiceCreateResponse(id = this.id)
