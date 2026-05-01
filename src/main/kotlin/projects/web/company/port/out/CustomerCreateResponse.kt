package projects.web.company.port.out

import projects.core.model.Company

class CompanyCreateResponse(
    val id: String,
    val name: String,
    val acronym: String?,
    val code: String?,
    val region: String?,
    val phone: String?,
    val email: String?,
    val active: Boolean,
)

fun Company.toResponse() = CompanyCreateResponse(
    id = this.id,
    name = this.name,
    acronym = this.acronym,
    code = this.code,
    region = this.region,
    phone = this.phone,
    email = this.email,
    active = this.active,
)
