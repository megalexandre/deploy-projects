package projects.web.projects.port.out

import com.fasterxml.jackson.annotation.JsonProperty
import projects.core.model.Coordinates
import projects.core.model.Project
import projects.web.address.port.out.AddressCreateResponse
import projects.web.address.port.out.toResponse
import java.time.Instant

class ProjectFindByIdResponse (

    val id: String,

    @field:JsonProperty("clienteId")
    val clientId: String,

    @field:JsonProperty("concessionaria")
    val utilityCompany: String,

    @field:JsonProperty("protocoloConcessionaria")
    val utilityProtocol: String,

    @field:JsonProperty("endereco")
    val address: AddressCreateResponse? = null,

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

    @field:JsonProperty("unidade_controladora")
    val unitControl: String,

    @field:JsonProperty("descrição")
    val description: String?,

    @field:JsonProperty("servicos")
    val servicesNames: List<String>?,

    @field:JsonProperty("tipo_projeto")
    val projectType: String,

    @field:JsonProperty("projeto_fast_track")
    val fastTrack: Boolean,

    @field:JsonProperty("coordenadas")
    val coordinates: Coordinates? = null,

    val createdAt: Instant? = null,
    val updatedAt: Instant? = null
)

fun Project.toFindByIdResponse() = ProjectFindByIdResponse(
    id = this.id,
    clientId = this.clientId,
    coordinates = this.coordinates,
    address = this.address?.toResponse(),
    utilityCompany = this.utilityCompany,
    utilityProtocol = this.utilityProtocol,
    customerClass = this.customerClass,
    integrator = this.integrator,
    modality = this.modality,
    framework = this.framework,
    dcProtection = this.dcProtection,
    systemPower = this.systemPower,
    unitControl = this.unitControl,
    description = this.description,
    servicesNames = this.servicesNames,
    projectType = this.projectType,
    fastTrack = this.fastTrack,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)
