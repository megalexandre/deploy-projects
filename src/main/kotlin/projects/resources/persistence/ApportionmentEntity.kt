package projects.resources.persistence

import jakarta.persistence.*
import projects.core.model.Apportionment
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "apportionments")
data class ApportionmentEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    var service: ServiceEntity,

    @Column(name = "consumer_unit", nullable = false)
    var consumerUnit: String,

    @Column(name = "address", nullable = false)
    var address: String,

    @Column(name = "classification", nullable = false)
    var classification: String,

    @Column(name = "percentage", nullable = false)
    var percentage: Int,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant,
) {
    @PreUpdate
    fun preUpdate() {
        updatedAt = Instant.now()
    }

    fun toDomain() = Apportionment(
        id = id.toString(),
        consumerUnit = consumerUnit,
        address = address,
        classification = classification,
        percentage = percentage,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun from(domain: Apportionment, service: ServiceEntity) = ApportionmentEntity(
            id = UUID.fromString(domain.id),
            service = service,
            consumerUnit = domain.consumerUnit,
            address = domain.address,
            classification = domain.classification,
            percentage = domain.percentage,
            createdAt = domain.createdAt ?: Instant.now(),
            updatedAt = domain.updatedAt ?: Instant.now()
        )
    }
}
