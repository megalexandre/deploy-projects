package projects.web.services.port.out

import projects.core.model.Apportionment
import projects.core.model.Address
import projects.core.model.Coordinates
import projects.core.model.Service
import projects.core.model.ServiceEntryItem
import java.math.BigDecimal
import java.time.LocalDate

class ServiceFindByIdResponse(
    val id: String,
    val type: String,
    val customerId: String,
    val concessionaireId: String,
    val openingDate: LocalDate,
    val amount: BigDecimal,
    val discountCouponPercentage: Int?,
    val observations: String?,
    val supplyVoltage: String?,
    val coordinates: CoordinatesResponse?,
    val generatingConsumerUnit: String?,
    val poleDistanceOver30m: Boolean,
    val constructionAddress: AddressResponse?,
    val generatingAddress: AddressResponse?,
    val serviceEntryItems: List<ServiceEntryItemResponse>,
    val apportionments: List<ApportionmentResponse>,
)

class CoordinatesResponse(val latitude: String, val longitude: String)

class AddressResponse(
    val id: String,
    val zipCode: String,
    val street: String,
    val number: String,
    val complement: String?,
    val neighborhood: String,
    val city: String,
    val state: String,
    val link: String?,
)

class ServiceEntryItemResponse(
    val id: String,
    val connectionType: String,
    val classification: String,
    val quantity: Int,
    val circuitBreaker: String,
)

class ApportionmentResponse(
    val id: String,
    val consumerUnit: String,
    val address: String,
    val classification: String,
    val percentage: Int,
)

fun Coordinates.toResponse() = CoordinatesResponse(latitude = latitude, longitude = longitude)

fun Address.toAddressResponse() = AddressResponse(
    id = id,
    zipCode = cep,
    street = address,
    number = number,
    complement = complement,
    neighborhood = neighborhood,
    city = city,
    state = state,
    link = link
)

fun ServiceEntryItem.toResponse() = ServiceEntryItemResponse(
    id = id,
    connectionType = connectionType,
    classification = classification,
    quantity = quantity,
    circuitBreaker = circuitBreaker
)

fun Apportionment.toResponse() = ApportionmentResponse(
    id = id,
    consumerUnit = consumerUnit,
    address = address,
    classification = classification,
    percentage = percentage
)

fun Service.toFindByIdResponse() = ServiceFindByIdResponse(
    id = id,
    type = type,
    customerId = customerId,
    concessionaireId = concessionaireId,
    openingDate = openingDate,
    amount = amount,
    discountCouponPercentage = discountCouponPercentage,
    observations = observations,
    supplyVoltage = supplyVoltage,
    coordinates = coordinates?.toResponse(),
    generatingConsumerUnit = generatingConsumerUnit,
    poleDistanceOver30m = poleDistanceOver30m,
    constructionAddress = constructionAddress?.toAddressResponse(),
    generatingAddress = generatingAddress?.toAddressResponse(),
    serviceEntryItems = serviceEntryItems.map { it.toResponse() },
    apportionments = apportionments.map { it.toResponse() }
)
