package projects.web.projects.port.`in`

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import projects.core.model.Project

class ProjectUpdateRequest (

    @field:JsonProperty("id")
    @field:NotBlank(message = "{project.id.notBlank}")
    @field:Size(max = 250, message = "{project.clientId.size}")
    @field:Pattern(regexp = "^[a-f0-9-]{36}$", flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{uuid.invalid}")
    val id: String?,

    @field:JsonProperty("clienteId")
    @field:NotBlank(message = "{project.clientId.notBlank}")
    @field:Size(min = 2, message = "{project.clientId.size}")
    @field:Size(max = 250, message = "{project.clientId.size}")
    val clientId: String?,

    @field:JsonProperty("concessionaria")
    @field:Size(min = 2, message = "{project.utilityCompany.size}")
    @field:Size(max = 250, message = "{project.utilityCompany.size}")
    val utilityCompany: String?,

    @field:JsonProperty("protocoloConcessionaria")
    @field:Size(min = 2, message = "{project.utilityProtocol.size}")
    @field:Size(max = 250, message = "{project.utilityProtocol.size}")
    val utilityProtocol: String?,

    @field:JsonProperty("classe")
    @field:Size(min = 2, message = "{project.customerClass.size}")
    @field:Size(max = 250, message = "{project.customerClass.size}")
    val customerClass: String?,

    @field:Size(min = 2, message = "{project.integrator.size}")
    @field:Size(max = 250, message = "{project.integrator.size}")
    val integrator: String?,

    @field:JsonProperty("modalidade")
    @field:Size(min = 2, message = "{project.modality.size}")
    @field:Size(max = 250, message = "{project.modality.size}")
    val modality: String?,

    @field:JsonProperty("enquadramento")
    @field:Size(min = 2, message = "{project.framework.size}")
    @field:Size(max = 250, message = "{project.framework.size}")
    val framework: String?,

    @field:JsonProperty("protecaoCC")
    @field:Size(min = 2, message = "{project.dcProtection.size}")
    @field:Size(max = 250, message = "{project.dcProtection.size}")
    val dcProtection: String?,

    @field:JsonProperty("potenciaSistema")
    @field:DecimalMin(value = "0.0", inclusive = true, message = "{project.systemPower.min}")
    val systemPower: Double?
) {
    fun toDomain() = Project(
        id = id!!,
        clientId = clientId!!,
        utilityCompany = utilityCompany!!,
        utilityProtocol = utilityProtocol!!,
        customerClass = customerClass!!,
        integrator = integrator!!,
        modality = modality!!,
        framework = framework!!,
        systemPower = systemPower!!,
        dcProtection = dcProtection!!
    )
}