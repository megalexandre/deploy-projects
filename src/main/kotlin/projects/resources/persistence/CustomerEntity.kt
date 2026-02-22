package projects.resources.persistence

import jakarta.persistence.*
import projects.core.model.Customer
import java.time.Instant
import java.util.*

@Entity
@Table(name = "customers")
data class CustomerEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: UUID,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "email", nullable = false, unique = true)
    var email: String,

    @Column(name = "tax_id", nullable = false, unique = true)
    var taxId: String,

    @Column(name = "phone")
    var phone: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant
) {

    @PreUpdate
    fun preUpdate() {
        updatedAt = Instant.now()
    }

    fun toDomain(): Customer =
        Customer(
            id = id.toString(),
            name = name,
            email = email,
            phone = phone,
            taxId = taxId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )

    companion object {
        fun from(domain: Customer): CustomerEntity =
            CustomerEntity(
                id = UUID.fromString( domain.id),
                name = domain.name,
                email = domain.email,
                taxId = domain.taxId,
                phone = domain.phone,
                createdAt = domain.createdAt ?: Instant.now(),
                updatedAt = domain.updatedAt ?: Instant.now()
            )
    }
}
