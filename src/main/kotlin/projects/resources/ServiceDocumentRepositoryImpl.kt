package projects.resources

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import projects.core.model.ServiceDocument
import projects.core.respository.ServiceDocumentRepository
import projects.resources.persistence.ServiceDocumentEntity
import java.util.UUID

@Repository
class ServiceDocumentRepositoryImpl(
    private val jpa: SpringDataServiceDocumentRepository
) : ServiceDocumentRepository {

    override fun save(doc: ServiceDocument): ServiceDocument =
        jpa.save(ServiceDocumentEntity.from(doc)).toDomain()

    override fun findAllByServiceId(serviceId: String): List<ServiceDocument> =
        jpa.findAllByServiceId(UUID.fromString(serviceId)).map { it.toDomain() }

    override fun findById(id: String): ServiceDocument? =
        jpa.findById(UUID.fromString(id)).map { it.toDomain() }.orElse(null)

    override fun deleteById(id: String) =
        jpa.deleteById(UUID.fromString(id))
}

@Repository
interface SpringDataServiceDocumentRepository : JpaRepository<ServiceDocumentEntity, UUID> {
    fun findAllByServiceId(serviceId: UUID): List<ServiceDocumentEntity>
}
