package projects.core.model

import java.time.Instant

data class Customer(
    val id: String,
    val name: String,
    val taxId: String,
    val addressId: String? = null,
    val address: Address? = null,
    val phone: String,
    val email: String,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null
)
