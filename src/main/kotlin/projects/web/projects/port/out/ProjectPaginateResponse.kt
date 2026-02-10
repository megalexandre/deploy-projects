package projects.web.projects.port.out

import com.fasterxml.jackson.annotation.JsonProperty
import projects.core.model.Project

class ProjectPaginateResponse (

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

    )

fun Project.toPaginateResponse() = ProjectPaginateResponse(
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
)
