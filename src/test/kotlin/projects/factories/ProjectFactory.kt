package projects.factories

import org.springframework.data.jpa.repository.JpaRepository
import projects.commons.Id
import projects.core.model.Project
import projects.resources.persistence.ProjectEntity
import java.time.Instant
import java.util.UUID

object ProjectFactory {

    fun project(
        id: String = Id.random(),
        clientId: String = "client-${Id.random().take(6)}",
        utilityCompany: String = "CEMIG",
        utilityProtocol: String = "PROT-${Id.random().take(8)}",
        customerClass: String = "Residencial",
        integrator: String = "Integrator ${Id.random().take(6)}",
        modality: String = "Geração Distribuída",
        framework: String = "Microgeração",
        dcProtection: String? = "Disjuntor CC 20A",
        systemPower: Double? = 5.5,
        createdAt: Instant? = Instant.now(),
        updatedAt: Instant? = Instant.now()
    ): Project = Project(
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

    fun entity(
        id: String = Id.random(),
        clientId: String = "client-${Id.random().take(6)}",
        utilityCompany: String = "CEMIG",
        utilityProtocol: String = "PROT-${Id.random().take(8)}",
        customerClass: String = "Residencial",
        integrator: String = "Integrator ${Id.random().take(6)}",
        modality: String = "Geração Distribuída",
        framework: String = "Microgeração",
        dcProtection: String? = "Disjuntor CC 20A",
        systemPower: Double? = 5.5,
        createdAt: Instant = Instant.now(),
        updatedAt: Instant = Instant.now()
    ): ProjectEntity = ProjectEntity(
        id = UUID.fromString(id),
        clientId = UUID.fromString(clientId),
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

    fun persist(
        jpa: JpaRepository<ProjectEntity, String>,
        count: Int = 1
    ): List<Project> =
        (1..count).map {
            jpa.save(entity()).toDomain()
        }
}