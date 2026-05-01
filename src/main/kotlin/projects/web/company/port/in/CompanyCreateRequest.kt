package projects.web.company.port.`in`

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import projects.commons.Id
import projects.core.model.Company

open class CompanyCreateRequest(

    @field:NotNull(message = "{company.name.notBlank}")
    @field:NotBlank(message = "{company.name.notBlank}")
    @field:Size(min = 3, max = 256, message = "{company.name.size}")
    val name: String?,

    val acronym: String? = null,
    val code: String? = null,
    val region: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val active: Boolean = true,
) {
    open fun toDomain() = Company(
        id = Id.random(),
        name = name!!.trim(),
        acronym = acronym,
        code = code,
        region = region,
        phone = phone,
        email = email,
        active = active,
    )
}
