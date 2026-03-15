package projects.web.projects.port.`in`

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.*
import projects.commons.Id
import projects.core.model.Project
import projects.core.model.Coordinates

open class ProjectCreateRequest (

    @field:JsonProperty("clienteId")
    @field:NotNull(message = "{project.clientId.notBlank}")
    @field:NotBlank(message = "{project.clientId.notBlank}")
    @field:Size(min = 2, message = "{project.clientId.size}")
    @field:Size(max = 250, message = "{project.clientId.size}")
    @field:Pattern(regexp = "^[a-f0-9-]{36}$", flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{uuid.invalid}")
    open val clientId: String?,

    @field:JsonProperty("enderecoId")
    @field:Pattern(regexp = "^[a-f0-9-]{36}$", flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{uuid.invalid}")
    open val addressId: String?,

    @field:JsonProperty("concessionaria")
    @field:NotNull(message = "{project.utilityCompany.notBlank}")
    @field:NotBlank(message = "{project.utilityCompany.notBlank}")
    @field:Size(min = 2, message = "{project.utilityCompany.size}")
    @field:Size(max = 250, message = "{project.utilityCompany.size}")
    open val utilityCompany: String?,

    @field:JsonProperty("protocoloConcessionaria")
    @field:NotNull(message = "{project.utilityProtocol.notBlank}")
    @field:NotBlank(message = "{project.utilityProtocol.notBlank}")
    @field:Size(min = 2, message = "{project.utilityProtocol.size}")
    @field:Size(max = 250, message = "{project.utilityProtocol.size}")
    open val utilityProtocol: String?,

    @field:JsonProperty("classe")
    @field:NotNull(message = "{project.customerClass.notBlank}")
    @field:NotBlank(message = "{project.customerClass.notBlank}")
    @field:Size(min = 2, message = "{project.customerClass.size}")
    @field:Size(max = 250, message = "{project.customerClass.size}")
    open val customerClass: String?,

    @field:NotNull(message = "{project.integrator.notBlank}")
    @field:NotBlank(message = "{project.integrator.notBlank}")
    @field:Size(min = 2, message = "{project.integrator.size}")
    @field:Size(max = 250, message = "{project.integrator.size}")
    open val integrator: String?,

    @field:JsonProperty("modalidade")
    @field:NotNull(message = "{project.modality.notBlank}")
    @field:NotBlank(message = "{project.modality.notBlank}")
    @field:Size(min = 2, message = "{project.modality.size}")
    @field:Size(max = 250, message = "{project.modality.size}")
    open val modality: String?,

    @field:JsonProperty("status")
    @field:NotNull(message = "{project.status.notBlank}")
    @field:NotBlank(message = "{project.status.notBlank}")
    @field:Size(min = 2, message = "{project.status.size}")
    @field:Size(max = 250, message = "{project.status.size}")
    open val status: String?,

    @field:JsonProperty("valor")
    @field:NotNull(message = "{project.amount.notBlank}")
    @field:NotBlank(message = "{project.amount.notBlank}")
    @field:DecimalMin(value = "0.0", inclusive = true, message = "{project.amount.min}")
    open val amount: String?,

    @field:JsonProperty("enquadramento")
    @field:NotNull(message = "{project.framework.notBlank}")
    @field:NotBlank(message = "{project.framework.notBlank}")
    @field:Size(min = 2, message = "{project.framework.size}")
    @field:Size(max = 250, message = "{project.framework.size}")
    open val framework: String?,

    @field:JsonProperty("protecaoCC")
    @field:Size(min = 2, message = "{project.dcProtection.size}")
    @field:Size(max = 250, message = "{project.dcProtection.size}")
    open val dcProtection: String?,

    @field:JsonProperty("potenciaSistema")
    @field:DecimalMin(value = "0.0", inclusive = true, message = "{project.systemPower.min}")
    open val systemPower: Double?,

    @field:JsonProperty("coordenadas")
    open val coordinates: CoordinatesCreateRequest?,

    @field:JsonProperty("unidade_controladora")
    @field:Size(min = 2)
    @field:Size(max = 250)
    open val unitControl: String,

    @field:JsonProperty("descrição")
    @field:Size(min = 2)
    @field:Size(max = 1024)
    open val description: String?,

    @field:JsonProperty("servicos")
    open val servicesNames: List<String>?,

    @field:JsonProperty("tipo_projeto")
    @field:Size(min = 2)
    @field:Size(max = 250)
    open val projectType: String,

    @field:JsonProperty("projeto_fast_track")
    open val fastTrack: Boolean,
) {
    open fun toDomain() = Project(
        id = Id.random(),
        clientId = clientId!!,
        addressId = addressId,
        utilityCompany = utilityCompany!!,
        utilityProtocol = utilityProtocol!!,
        customerClass = customerClass!!,
        integrator = integrator!!,
        modality = modality!!,
        framework = framework!!,
        systemPower = systemPower,
        dcProtection = dcProtection,
        coordinates = coordinates?.toDomain(),
        unitControl = unitControl,
        description = description,
        servicesNames = servicesNames,
        projectType = projectType,
        fastTrack = fastTrack,
        status = status!!,
        amount = amount!!.toBigDecimal(),
    )
}


class CoordinatesCreateRequest(
    @field:JsonProperty("latitude")
    @field:Size(min = 2)
    @field:Size(max = 250)
    val latitude: String?,

    @field:JsonProperty("longitude")
    @field:Size(min = 2)
    @field:Size(max = 250)
    val longitude: String?,
){
    fun toDomain() = Coordinates(
        latitude = latitude!!,
        longitude = longitude!!
    )
}