package projects.web.company.port.`in`

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import projects.core.model.Company

class CompanyUpdateRequest(

    @field:NotBlank(message = "{project.id.notBlank}")
    @field:Size(max = 250, message = "{project.clientId.size}")
    @field:Pattern(regexp = "^[a-f0-9-]{36}$", flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{uuid.invalid}")
    val id: String?,

    name: String?,
): CompanyCreateRequest(
    name = name
) {
    override fun toDomain(): Company {
        return super.toDomain().copy(
            id = this.id!!
        )
    }
}

