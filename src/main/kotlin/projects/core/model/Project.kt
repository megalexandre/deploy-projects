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
    val coordinates: Coordinates? = null,
    val unitControl: String,
    val description: String?,
    val servicesNames: List<String>?,
    val projectType: String,
    val fastTrack: Boolean,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null
)

data class Coordinates(
    val lat: String,
    val long: String
)

