package projects.core.model

import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate

data class Service(
    val id: String,
    val type: String,
    val customerId: String,
    val concessionaireId: String,
    val openingDate: LocalDate,
    val amount: BigDecimal,
    val discountCouponPercentage: Int?,
    val observations: String?,
    val supplyVoltage: String?,
    val coordinates: Coordinates?,
    val generatingConsumerUnit: String?,
    val poleDistanceOver30m: Boolean,
    val constructionAddress: Address?,
    val generatingAddress: Address?,
    val serviceEntryItems: List<ServiceEntryItem>,
    val apportionments: List<Apportionment>,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null
)

data class ServiceEntryItem(
    val id: String,
    val connectionType: String,
    val classification: String,
    val quantity: Int,
    val circuitBreaker: String
)

data class Apportionment(
    val id: String,
    val consumerUnit: String,
    val address: String,
    val classification: String,
    val percentage: Int
)
