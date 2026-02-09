package projects.core.model

data class Project(
    val id: String,
    val clientId: String,
    val utilityCompany: String,
    val utilityProtocol: String,
    val customerClass: String,
    val integrator: String,
    val modality: String,
    val framework: String,

    val dcProtection: String?,
    val systemPower: Double?
)


