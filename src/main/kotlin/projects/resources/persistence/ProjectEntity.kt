package projects.resources.persistence

import jakarta.persistence.*
import projects.core.model.Project
import java.time.Instant
import java.util.*
import projects.commons.Id as identity

@Entity
@Table(name = "projects")
data class ProjectEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: UUID,

    @Column(name = "client_id", nullable = false)
    var clientId: UUID,

    @Column(name = "utility_company", nullable = false)
    var utilityCompany: String,

    @Column(name = "utility_protocol", nullable = false)
    var utilityProtocol: String,

    @Column(name = "customer_class", nullable = false)
    var customerClass: String,

    @Column(name = "integrator", nullable = false)
    var integrator: String,

    @Column(name = "modality", nullable = false)
    var modality: String,

    @Column(name = "framework", nullable = false)
    var framework: String,

    @Column(name = "dc_protection")
    var dcProtection: String?,

    @Column(name = "system_power")
    var systemPower: Double?,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant
) {

    @PreUpdate
    fun preUpdate() {
        updatedAt = Instant.now()
    }

    fun toDomain(): Project =
        Project(
            id = id.toString(),
            clientId = clientId.toString(),
            utilityCompany = utilityCompany,
            utilityProtocol = utilityProtocol,
            customerClass = customerClass,
            integrator = integrator,
            modality = modality,
            framework = framework,
            dcProtection = dcProtection,
            systemPower = systemPower,
            createdAt = createdAt,
            updatedAt = updatedAt
        )

    companion object {
        fun from(domain: Project): ProjectEntity =
            ProjectEntity(
                id = UUID.fromString(domain.id) ,
                clientId = UUID.fromString(domain.clientId),
                utilityCompany = domain.utilityCompany,
                utilityProtocol = domain.utilityProtocol,
                customerClass = domain.customerClass,
                integrator = domain.integrator,
                modality = domain.modality,
                framework = domain.framework,
                dcProtection = domain.dcProtection,
                systemPower = domain.systemPower,
                createdAt = domain.createdAt ?: Instant.now(),
                updatedAt = domain.updatedAt ?: Instant.now()
            )
    }
}
