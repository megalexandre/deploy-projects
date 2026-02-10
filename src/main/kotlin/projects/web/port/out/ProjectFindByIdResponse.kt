package projects.web.port.out

import projects.core.model.Project
import java.time.Instant

class ProjectFindByIdResponse (
    val id: String,
    val clientId: String,
    val utilityCompany: String,
    val utilityProtocol: String,
    val customerClass: String,
    val integrator: String,
    val modality: String,
    val framework: String,
    val dcProtection: String?,
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
