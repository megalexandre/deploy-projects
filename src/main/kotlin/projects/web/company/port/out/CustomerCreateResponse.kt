package projects.web.company.port.out

import projects.core.model.Company

class CompanyCreateResponse(
    val id: String,
    val name: String,
)

fun Company.toResponse() = CompanyCreateResponse(
    id = this.id,
    name = this.name,
)
