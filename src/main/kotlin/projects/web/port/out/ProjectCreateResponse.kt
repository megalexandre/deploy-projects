package projects.web.port.out

import projects.core.model.Project

class ProjectCreateResponse (
    val id: String,
    val clientId: String,
    val utilityCompany: String,
    val utilityProtocol: String,
    val customerClass: String,
    val integrator: String,
    val modality: String,
    val framework: String,
    val systemPower: Double?,
    val dcProtection: String?
)

fun Project.toResponse() = ProjectCreateResponse(
    id = this.id,
    clientId = this.clientId,
    utilityCompany = this.utilityCompany,
    utilityProtocol = this.utilityProtocol,
    customerClass = this.customerClass,
    integrator = this.integrator,
    modality = this.modality,
    framework = this.framework,
    systemPower = this.systemPower,
    dcProtection = this.dcProtection
)