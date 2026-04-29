package projects.web.services.port.`in`

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import projects.core.model.Project
import projects.web.projects.port.`in`.CoordinatesCreateRequest
import projects.web.projects.port.`in`.ProjectCreateRequest

class ProjectUpdateRequest(

    @field:JsonProperty("id")
    @field:NotBlank(message = "{project.id.notBlank}")
    @field:Size(max = 250, message = "{project.clientId.size}")
    @field:Pattern(regexp = "^[a-f0-9-]{36}$", flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{uuid.invalid}")
    val id: String?,

    addressId: String?,
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
    systemPower: Double?,
    coordinates: CoordinatesCreateRequest?,
    description: String?,
    servicesNames: List<String>?,
    projectType: String,
    fastTrack: Boolean,
    unitControl: String,

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
    systemPower = systemPower,
    addressId = addressId,
    coordinates = coordinates,
    servicesNames = servicesNames,
    projectType = projectType,
    description = description,
    fastTrack = fastTrack,
    unitControl = unitControl
)  {

    override fun toDomain(): Project {
        return super.toDomain().copy(
            id = this.id!!
        )
    }
}