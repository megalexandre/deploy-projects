package projects.web.projects.port.`in`

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import projects.core.model.Project
import java.math.BigDecimal

class ProjectUpdateRequest(

    @field:JsonProperty("id")
    @field:NotBlank(message = "{project.id.notBlank}")
    @field:Size(max = 250, message = "{project.clientId.size}")
    @field:Pattern(regexp = "^[a-f0-9-]{36}$", flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{uuid.invalid}")
    val id: String?,

    clientId: String?,
    utilityCompany: String?,
    utilityProtocol: String?,
    customerClass: String?,
    integrator: String?,
    modality: String?,
    framework: String?,
    status: String?,
    amount: String?,
    dcProtection: String?,
    systemPower: Double?

): ProjectCreateRequest(
    clientId = clientId,
    utilityCompany = utilityCompany,
    utilityProtocol = utilityProtocol,
    customerClass = customerClass,
    integrator = integrator,
    modality = modality,
    status = status,
    amount = amount,
    framework = framework,
    dcProtection = dcProtection,
    systemPower = systemPower
)  {

    override fun toDomain(): Project {
        return super.toDomain().copy(
            id = this.id!!
        )
    }
}