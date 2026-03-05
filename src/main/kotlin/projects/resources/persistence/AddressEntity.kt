package projects.resources.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import projects.core.model.Address
import java.util.*

@Entity
@Table(name = "address")
data class AddressEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: UUID,

    @Column(name = "link", nullable = false)
    var link: String?,

    @Column(name = "place", nullable = false)
    var place: String,

    @Column(name = "cep", nullable = false)
    val cep: String,

    @Column(name = "number", nullable = false)
    val number: String,

    @Column(name = "address", nullable = false)
    val address: String,

    @Column(name = "complement", nullable = true)
    val complement: String?,

    @Column(name = "neighborhood", nullable = false)
    val neighborhood: String,

    @Column(name = "city", nullable = false)
    val city: String,

    @Column(name = "state", nullable = false)
    val state: String

) {

    fun toDomain(): Address =
        Address(
            id = id.toString(),
            link = link,
            place = place,
            cep = cep,
            number = number,
            address = address,
            complement = complement,
            neighborhood = neighborhood,
            city = city,
            state = state
        )

    companion object {
        fun from(domain: Address): AddressEntity =
            AddressEntity(
                id = UUID.fromString( domain.id),
                link = domain.link,
                place = domain.place,
                cep = domain.cep,
                number = domain.number,
                address = domain.address,
                complement = domain.complement,
                neighborhood = domain.neighborhood,
                city = domain.city,
                state = domain.state
            )
    }
}
