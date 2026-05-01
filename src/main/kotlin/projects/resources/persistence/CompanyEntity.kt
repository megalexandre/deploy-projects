package projects.resources.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import projects.core.model.Company
import java.util.*

@Entity
@Table(name = "concessionaires")
data class CompanyEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: UUID,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "acronym")
    var acronym: String?,

    @Column(name = "code")
    var code: String?,

    @Column(name = "region")
    var region: String?,

    @Column(name = "phone")
    var phone: String?,

    @Column(name = "email")
    var email: String?,

    @Column(name = "active", nullable = false)
    var active: Boolean,

) {

    fun toDomain(): Company =
        Company(
            id = id.toString(),
            name = name,
            acronym = acronym,
            code = code,
            region = region,
            phone = phone,
            email = email,
            active = active,
        )

    companion object {
        fun from(domain: Company): CompanyEntity =
            CompanyEntity(
                id = UUID.fromString(domain.id),
                name = domain.name,
                acronym = domain.acronym,
                code = domain.code,
                region = domain.region,
                phone = domain.phone,
                email = domain.email,
                active = domain.active,
            )
    }
}
