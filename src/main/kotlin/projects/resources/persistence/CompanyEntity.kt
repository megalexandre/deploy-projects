package projects.resources.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import projects.core.model.Company
import java.util.*

@Entity
@Table(name = "company")
data class CompanyEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: UUID,

    @Column(name = "name", nullable = false)
    var name: String,

) {

    fun toDomain(): Company =
        Company(
            id = id.toString(),
            name = name,
        )

    companion object {
        fun from(domain: Company): CompanyEntity =
            CompanyEntity(
                id = UUID.fromString( domain.id),
                name = domain.name
            )
    }
}
