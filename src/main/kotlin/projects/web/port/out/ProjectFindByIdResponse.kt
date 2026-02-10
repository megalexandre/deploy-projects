package projects.web.port.out

import com.fasterxml.jackson.annotation.JsonProperty
import projects.core.model.Project
import java.time.Instant

class ProjectFindByIdResponse (

    val id: String,

    @field:JsonProperty("clienteId")
    val clientId: String,

    @field:JsonProperty("concessionaria")
    val utilityCompany: String,

    @field:JsonProperty("protocoloConcessionaria")
    val utilityProtocol: String,

    @field:JsonProperty("classe")
    val customerClass: String,

    val integrator: String,

    @field:JsonProperty("modalidade")
    val modality: String,

    @field:JsonProperty("enquadramento")
    val framework: String,

    @field:JsonProperty("protecaoCC")
    val dcProtection: String?,

    @field:JsonProperty("potenciaSistema")
    val systemPower: Double?,

    val createdAt: Instant? = null,
    val updatedAt: Instant? = null
)

fun Project.toFindByIdResponse() = ProjectFindByIdResponse(
    id = this.id,
    clientId = this.clientId,
    utilityCompany = this.utilityCompany,
    utilityProtocol = this.utilityProtocol,
    customerClass = this.customerClass,
    integrator = this.integrator,
    modality = this.modality,
    framework = this.framework,
    dcProtection = this.dcProtection,
    systemPower = this.systemPower,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)
