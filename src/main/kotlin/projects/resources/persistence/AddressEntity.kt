package projects.resources.persistence

import jakarta.persistence.*
import projects.core.model.Address
import projects.core.model.User
import java.time.Instant
import java.util.*

@Entity
@Table(name = "address")
data class AddressEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: UUID,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "link", nullable = false)
    var link: String,

    @Column(name = "place", nullable = false)
    var place: String,

) {

    fun toDomain(): Address =
        Address(
            id = id.toString(),
            name = name,
            link = link,
            place = place
        )

    companion object {
        fun from(domain: Address): AddressEntity =
            AddressEntity(
                id = UUID.fromString( domain.id),
                name = domain.name,
                place = domain.place,
                link = domain.link,
            )
    }
}
