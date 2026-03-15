package projects.resources.persistence

import jakarta.persistence.*
import projects.core.model.Coordinates
import projects.core.model.Project
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Entity
@Table(name = "projects")
data class ProjectEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: UUID,

    @Column(name = "client_id", nullable = false)
    var clientId: UUID,

    @Column(name = "address_id")
    var addressId: UUID?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    var address: AddressEntity?,

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

    @Column(name = "status")
    var status: String,

    @Column(name = "amount")
    var amount: BigDecimal,

    @Column(name = "dc_protection")
    var dcProtection: String?,

    @Column(name = "system_power")
    var systemPower: Double?,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant,

    @Column(name = "latitude")
    var latitude: String?,

    @Column(name = "longitude")
    var longitude: String?,

    @Column(name = "unit_control", nullable = false)
    var unitControl: String,

    @Column(name = "description", length = 1024)
    var description: String?,

    @Column(name = "services_names")
    var servicesNames: String?,

    @Column(name = "project_type", nullable = false)
    var projectType: String,

    @Column(name = "fast_track", nullable = false)
    var fastTrack: Boolean,
    ) {

    @PreUpdate
    fun preUpdate() {
        updatedAt = Instant.now()
    }

    fun toDomain(): Project {
        val lat = latitude
        val long = longitude
        val coordinates = if (lat != null && long != null) Coordinates(lat, long) else null

        return Project(
            id = id.toString(),
            clientId = clientId.toString(),
            addressId = addressId?.toString(),
            address = address?.toDomain(),
            utilityCompany = utilityCompany,
            utilityProtocol = utilityProtocol,
            customerClass = customerClass,
            integrator = integrator,
            modality = modality,
            framework = framework,
            dcProtection = dcProtection,
            systemPower = systemPower,
            createdAt = createdAt,
            amount = amount,
            status = status,
            coordinates = coordinates,
            unitControl = unitControl,
            description = description,
            servicesNames = servicesNames?.split(",")?.map { it.trim() },
            projectType = projectType,
            fastTrack = fastTrack,
            updatedAt = updatedAt
        )
    }

    companion object {
        fun from(domain: Project): ProjectEntity =
            ProjectEntity(
                id = UUID.fromString(domain.id) ,
                clientId = UUID.fromString(domain.clientId),
                addressId = domain.addressId?.let {  UUID.fromString(it)} ,
                address = domain.address?.let { AddressEntity.from(it) },
                utilityCompany = domain.utilityCompany,
                utilityProtocol = domain.utilityProtocol,
                customerClass = domain.customerClass,
                integrator = domain.integrator,
                modality = domain.modality,
                framework = domain.framework,
                dcProtection = domain.dcProtection,
                systemPower = domain.systemPower,
                amount = domain.amount,
                status = domain.status,
                latitude = domain.coordinates?.lat,
                longitude = domain.coordinates?.long,
                unitControl = domain.unitControl,
                description = domain.description,
                servicesNames = domain.servicesNames?.joinToString(","),
                projectType = domain.projectType,
                fastTrack = domain.fastTrack,
                createdAt = domain.createdAt ?: Instant.now(),
                updatedAt = domain.updatedAt ?: Instant.now()
            )
    }
}
