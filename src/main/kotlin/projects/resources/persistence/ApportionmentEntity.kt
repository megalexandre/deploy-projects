package projects.resources.persistence

import jakarta.persistence.*
import projects.core.model.Apportionment
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

    @Column(name = "\"class\"", nullable = false)
    var classification: String,

    @Column(name = "percentage", nullable = false)
    var percentage: Int,
) {
    fun toDomain() = Apportionment(
        id = id.toString(),
        consumerUnit = consumerUnit,
        address = address,
        classification = classification,
        percentage = percentage
    )

    companion object {
        fun from(domain: Apportionment, service: ServiceEntity) = ApportionmentEntity(
            id = UUID.fromString(domain.id),
            service = service,
            consumerUnit = domain.consumerUnit,
            address = domain.address,
            classification = domain.classification,
            percentage = domain.percentage
        )
    }
}
