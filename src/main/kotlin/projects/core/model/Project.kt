package projects.core.model

import java.math.BigDecimal
import java.time.Instant

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
    val systemPower: Double?,
    val addressId: String?,
    val address: Address? = null,
    val amount : BigDecimal,
    val status: String,

    val createdAt: Instant? = null,
    val updatedAt: Instant? = null
)


