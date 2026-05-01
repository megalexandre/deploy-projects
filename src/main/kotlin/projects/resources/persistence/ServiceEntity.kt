package projects.resources.persistence

import jakarta.persistence.*
import projects.core.model.Apportionment
import projects.core.model.Coordinates
import projects.core.model.Service
import projects.core.model.ServiceEntryItem
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "services")
data class ServiceEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: UUID,

    @Column(name = "type", nullable = false)
    var type: String,

    @Column(name = "customer_id", nullable = false)
    var customerId: UUID,

    @Column(name = "concessionaire_id", nullable = false)
    var concessionaireId: UUID,

    @Column(name = "opening_date", nullable = false)
    var openingDate: LocalDate,

    @Column(name = "amount", nullable = false)
    var amount: BigDecimal,

    @Column(name = "discount_coupon_percentage")
    var discountCouponPercentage: Int?,

    @Column(name = "observations")
    var observations: String?,

    @Column(name = "supply_voltage")
    var supplyVoltage: String?,

    @Column(name = "latitude", columnDefinition = "numeric")
    var latitude: Double?,

    @Column(name = "longitude", columnDefinition = "numeric")
    var longitude: Double?,

    @Column(name = "generating_consumer_unit")
    var generatingConsumerUnit: String?,

    @Column(name = "pole_distance_over_30m", nullable = false)
    var poleDistanceOver30m: Boolean,

    @Column(name = "construction_address_id")
    var constructionAddressId: UUID?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "construction_address_id", insertable = false, updatable = false)
    var constructionAddress: AddressEntity?,

    @Column(name = "generating_address_id")
    var generatingAddressId: UUID?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generating_address_id", insertable = false, updatable = false)
    var generatingAddress: AddressEntity?,

    @OneToMany(mappedBy = "service", cascade = [CascadeType.ALL], orphanRemoval = true)
    var entryItems: MutableList<ServiceEntryItemEntity> = mutableListOf(),

    @OneToMany(mappedBy = "service", cascade = [CascadeType.ALL], orphanRemoval = true)
    var apportionments: MutableList<ApportionmentEntity> = mutableListOf(),

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant,
) {

    @PreUpdate
    fun preUpdate() {
        updatedAt = Instant.now()
    }

    fun toDomain(): Service {
        val lat = latitude
        val lng = longitude
        val coordinates = if (lat != null && lng != null) Coordinates(lat, lng) else null

        return Service(
            id = id.toString(),
            type = type,
            customerId = customerId.toString(),
            concessionaireId = concessionaireId.toString(),
            openingDate = openingDate,
            amount = amount,
            discountCouponPercentage = discountCouponPercentage,
            observations = observations,
            supplyVoltage = supplyVoltage,
            coordinates = coordinates,
            generatingConsumerUnit = generatingConsumerUnit,
            poleDistanceOver30m = poleDistanceOver30m,
            constructionAddress = constructionAddress?.toDomain(),
            generatingAddress = generatingAddress?.toDomain(),
            serviceEntryItems = entryItems.map { it.toDomain() },
            apportionments = apportionments.map { it.toDomain() },
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    companion object {
        fun from(domain: Service): ServiceEntity {
            val entity = ServiceEntity(
                id = UUID.fromString(domain.id),
                type = domain.type,
                customerId = UUID.fromString(domain.customerId),
                concessionaireId = UUID.fromString(domain.concessionaireId),
                openingDate = domain.openingDate,
                amount = domain.amount,
                discountCouponPercentage = domain.discountCouponPercentage,
                observations = domain.observations,
                supplyVoltage = domain.supplyVoltage,
                latitude = domain.coordinates?.latitude,
                longitude = domain.coordinates?.longitude,
                generatingConsumerUnit = domain.generatingConsumerUnit,
                poleDistanceOver30m = domain.poleDistanceOver30m,
                constructionAddressId = domain.constructionAddress?.id?.let { UUID.fromString(it) },
                constructionAddress = null,
                generatingAddressId = domain.generatingAddress?.id?.let { UUID.fromString(it) },
                generatingAddress = null,
                createdAt = domain.createdAt ?: Instant.now(),
                updatedAt = domain.updatedAt ?: Instant.now()
            )

            entity.entryItems = domain.serviceEntryItems
                .map { ServiceEntryItemEntity.from(it, entity) }
                .toMutableList()

            entity.apportionments = domain.apportionments
                .map { ApportionmentEntity.from(it, entity) }
                .toMutableList()

            return entity
        }
    }
}
