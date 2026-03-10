package projects.resources

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import projects.core.model.Customer
import projects.core.respository.CustomerRepository
import projects.resources.persistence.CustomerEntity
import java.util.*

@Repository
class CustomerRepositoryImpl(
    private val jpa: SpringDataCustomerRepository
): CustomerRepository {

    override fun save(t: Customer): Customer  =
         jpa.save(CustomerEntity.from(t)).toDomain()

    override fun delete(id: String) {
        val key = UUID.fromString(id)

        if (jpa.existsById(key)) {
            jpa.deleteById(key)
        }
    }

    override fun findAll(): List<Customer> =
        jpa.findAll(Sort.by(Sort.Direction.ASC, "id")).map { it.toDomain() }

    override fun findById(id: String): Customer? =
        jpa.findByIdWithAddress(UUID.fromString(id)).orElse(null)?.toDomain()

}

@Repository
interface SpringDataCustomerRepository : JpaRepository<CustomerEntity, UUID> {
    @Query("SELECT c FROM CustomerEntity c LEFT JOIN FETCH c.address WHERE c.id = :id")
    fun findByIdWithAddress(id: UUID): Optional<CustomerEntity>
}
