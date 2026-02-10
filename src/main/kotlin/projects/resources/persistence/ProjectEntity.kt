package projects.resources.persistence

import jakarta.persistence.*
import java.time.Instant
import java.util.UUID
import projects.core.model.Project

@Entity
@Table(name = "projects")
data class ProjectEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: String = UUID.randomUUID().toString(),

    @Column(name = "client_id", nullable = false)
    var clientId: String = "",

    @Column(name = "utility_company", nullable = false)
    var utilityCompany: String = "",

    @Column(name = "utility_protocol", nullable = false)
    var utilityProtocol: String = "",

    @Column(name = "customer_class", nullable = false)
    var customerClass: String = "",

    @Column(name = "integrator", nullable = false)
    var integrator: String = "",

    @Column(name = "modality", nullable = false)
    var modality: String = "",

    @Column(name = "framework", nullable = false)
    var framework: String = "",

    @Column(name = "dc_protection")
    var dcProtection: String? = null,

    @Column(name = "system_power")
    var systemPower: Double? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
) {
    @PreUpdate
    fun preUpdate() {
        updatedAt = Instant.now()
    }

    fun toDomain(): Project =
        Project(
            id = id,
            clientId = clientId,
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
                id = domain.id.ifBlank { UUID.randomUUID().toString() },
                clientId = domain.clientId,
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
