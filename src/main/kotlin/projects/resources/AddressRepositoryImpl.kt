package projects.resources

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import projects.core.model.Address
import projects.core.respository.AddressRepository
import projects.resources.persistence.AddressEntity
import java.util.*

@Repository
class AddressRepositoryImpl(
    private val jpa: SpringDataAddressRepository
): AddressRepository {

    override fun save(t: Address): Address  =
         jpa.save(AddressEntity.from(t)).toDomain()

    override fun delete(id: String) {
        val key = UUID.fromString(id)

        if (jpa.existsById(key)) {
            jpa.deleteById(key)
        }
    }

    override fun findAll(): List<Address> =
        jpa.findAll(Sort.by(Sort.Direction.ASC, "id")).map { it.toDomain() }

    override fun findById(id: String): Address? =
        jpa.findById(UUID.fromString(id) ).orElse(null)?.toDomain()

}

@Repository
interface SpringDataAddressRepository : JpaRepository<AddressEntity, UUID>
