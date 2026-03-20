package projects.resources

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import projects.core.model.Company
import projects.core.respository.CompanyRepository
import projects.resources.persistence.CompanyEntity
import java.util.*

@Repository
class CompanyRepositoryImpl(
    private val jpa: SpringDataCompanyRepository
): CompanyRepository {

    override fun save(t: Company): Company {
        return jpa.save(CompanyEntity.from(t)).toDomain()
    }

    override fun delete(id: String) {
        val key = UUID.fromString(id)

        if (jpa.existsById(key)) {
            jpa.deleteById(key)
        }
    }

    override fun findAll(): List<Company> =
        jpa.findAll(Sort.by(Sort.Direction.ASC, "id")).map { it.toDomain() }

    override fun findById(id: String): Company? =
        jpa.findById(UUID.fromString(id) ).orElse(null)?.toDomain()

}

@Repository
interface SpringDataCompanyRepository : JpaRepository<CompanyEntity, UUID>
