package projects.web.services.port.`in`

import jakarta.validation.Valid
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import projects.commons.Id
import projects.core.model.Service
import projects.web.projects.port.`in`.CoordinatesCreateRequest
import java.math.BigDecimal
import java.time.LocalDate

class ServiceCreateRequest(

    @field:NotNull
    @field:NotBlank
    val type: String?,

    @field:NotNull
    @field:Pattern(regexp = "^[a-f0-9-]{36}$", flags = [Pattern.Flag.CASE_INSENSITIVE])
    val customerId: String?,

    @field:NotNull
    @field:Pattern(regexp = "^[a-f0-9-]{36}$", flags = [Pattern.Flag.CASE_INSENSITIVE])
    val concessionaireId: String?,

    @field:NotNull
    val openingDate: LocalDate?,

    @field:NotNull
    @field:DecimalMin(value = "0.0", inclusive = true)
    val amount: BigDecimal?,

    @field:Min(0)
    @field:Max(100)
    val discountCouponPercentage: Int? = null,

    val observations: String? = null,

    val supplyVoltage: String? = null,

    @field:Valid
    val coordinates: CoordinatesCreateRequest? = null,

    val generatingConsumerUnit: String? = null,

    val poleDistanceOver30m: Boolean = false,

    @field:Valid
    val constructionAddress: ServiceAddressRequest? = null,

    @field:Valid
    val generatingAddress: ServiceAddressRequest? = null,

    @field:Valid
    val serviceEntryItems: List<ServiceEntryItemRequest> = emptyList(),

    @field:Valid
    val apportionments: List<ApportionmentRequest> = emptyList(),
) {
    fun toDomain() = Service(
        id = Id.random(),
        type = type!!,
        customerId = customerId!!,
        concessionaireId = concessionaireId!!,
        openingDate = openingDate!!,
        amount = amount!!,
        discountCouponPercentage = discountCouponPercentage,
        observations = observations,
        supplyVoltage = supplyVoltage,
        coordinates = coordinates?.toDomain(),
        generatingConsumerUnit = generatingConsumerUnit,
        poleDistanceOver30m = poleDistanceOver30m,
        constructionAddress = constructionAddress?.toDomain(),
        generatingAddress = generatingAddress?.toDomain(),
        serviceEntryItems = serviceEntryItems.map { it.toDomain() },
        apportionments = apportionments.map { it.toDomain() }
    )
}
