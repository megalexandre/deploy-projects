package projects.resources.persistence

import jakarta.persistence.*
import projects.core.model.ServiceEntryItem
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "service_entry_items")
data class ServiceEntryItemEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    var service: ServiceEntity,

    @Column(name = "connection_type", nullable = false)
    var connectionType: String,

    @Column(name = "classification", nullable = false)
    var classification: String,

    @Column(name = "quantity", nullable = false)
    var quantity: Int,

    @Column(name = "circuit_breaker", nullable = false)
    var circuitBreaker: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant,
) {
    @PreUpdate
    fun preUpdate() {
        updatedAt = Instant.now()
    }

    fun toDomain() = ServiceEntryItem(
        id = id.toString(),
        connectionType = connectionType,
        classification = classification,
        quantity = quantity,
        circuitBreaker = circuitBreaker,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun from(domain: ServiceEntryItem, service: ServiceEntity) = ServiceEntryItemEntity(
            id = UUID.fromString(domain.id),
            service = service,
            connectionType = domain.connectionType,
            classification = domain.classification,
            quantity = domain.quantity,
            circuitBreaker = domain.circuitBreaker,
            createdAt = domain.createdAt ?: Instant.now(),
            updatedAt = domain.updatedAt ?: Instant.now()
        )
    }
}
