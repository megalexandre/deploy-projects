package projects.web.projects.port.`in`

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import projects.commons.Id
import projects.core.model.Project

class ProjectCreateRequest (

    @field:JsonProperty("clienteId")
    @field:NotNull(message = "{project.clientId.notBlank}")
    @field:NotBlank(message = "{project.clientId.notBlank}")
    @field:Size(min = 2, message = "{project.clientId.size}")
    @field:Size(max = 250, message = "{project.clientId.size}")
    @field:Pattern(regexp = "^[a-f0-9-]{36}$", flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{uuid.invalid}")
    val clientId: String?,

    @field:JsonProperty("concessionaria")
    @field:NotNull(message = "{project.utilityCompany.notBlank}")
    @field:NotBlank(message = "{project.utilityCompany.notBlank}")
    @field:Size(min = 2, message = "{project.utilityCompany.size}")
    @field:Size(max = 250, message = "{project.utilityCompany.size}")
    val utilityCompany: String?,

    @field:JsonProperty("protocoloConcessionaria")
    @field:NotNull(message = "{project.utilityProtocol.notBlank}")
    @field:NotBlank(message = "{project.utilityProtocol.notBlank}")
    @field:Size(min = 2, message = "{project.utilityProtocol.size}")
    @field:Size(max = 250, message = "{project.utilityProtocol.size}")
    val utilityProtocol: String?,

    @field:JsonProperty("classe")
    @field:NotNull(message = "{project.customerClass.notBlank}")
    @field:NotBlank(message = "{project.customerClass.notBlank}")
    @field:Size(min = 2, message = "{project.customerClass.size}")
    @field:Size(max = 250, message = "{project.customerClass.size}")
    val customerClass: String?,

    @field:NotNull(message = "{project.integrator.notBlank}")
    @field:NotBlank(message = "{project.integrator.notBlank}")
    @field:Size(min = 2, message = "{project.integrator.size}")
    @field:Size(max = 250, message = "{project.integrator.size}")
    val integrator: String?,

    @field:JsonProperty("modalidade")
    @field:NotNull(message = "{project.modality.notBlank}")
    @field:NotBlank(message = "{project.modality.notBlank}")
    @field:Size(min = 2, message = "{project.modality.size}")
    @field:Size(max = 250, message = "{project.modality.size}")
    val modality: String?,

    @field:JsonProperty("enquadramento")
    @field:NotNull(message = "{project.framework.notBlank}")
    @field:NotBlank(message = "{project.framework.notBlank}")
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
        id = Id.random(),
        clientId = clientId!!,
        utilityCompany = utilityCompany!!,
        utilityProtocol = utilityProtocol!!,
        customerClass = customerClass!!,
        integrator = integrator!!,
        modality = modality!!,
        framework = framework!!,
        systemPower = systemPower,
        dcProtection = dcProtection
    )
}