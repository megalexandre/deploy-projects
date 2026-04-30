package projects.resources

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import projects.core.model.Service
import projects.core.respository.ServiceRepository
import projects.resources.persistence.ServiceEntity
import java.util.Optional
import java.util.UUID

@Repository
class ServiceRepositoryImpl(
    private val jpa: SpringDataServiceRepository
) : ServiceRepository {

    override fun save(t: Service): Service =
        jpa.save(ServiceEntity.from(t)).toDomain()

    override fun delete(id: String) {
        val key = UUID.fromString(id)
        if (jpa.existsById(key)) {
            jpa.deleteById(key)
        }
    }

    override fun findAll(): List<Service> =
        jpa.findAll(Sort.by(Sort.Direction.ASC, "id")).map { it.toDomain() }

    override fun findById(id: String): Service? =
        jpa.findByIdWithRelations(UUID.fromString(id)).orElse(null)?.toDomain()
}

@Repository
interface SpringDataServiceRepository : JpaRepository<ServiceEntity, UUID> {
    @Query("""
        SELECT s FROM ServiceEntity s
        LEFT JOIN FETCH s.constructionAddress
        LEFT JOIN FETCH s.generatingAddress
        WHERE s.id = :id
    """)
    fun findByIdWithRelations(id: UUID): Optional<ServiceEntity>
}
