package projects.steps

import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.en.Given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import projects.core.model.Service
import projects.resources.SpringDataAddressRepository
import projects.resources.SpringDataServiceRepository
import projects.resources.persistence.AddressEntity
import projects.resources.persistence.ServiceEntity

class ServicesSteps {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var serviceJpa: SpringDataServiceRepository

    @Autowired
    private lateinit var addressJpa: SpringDataAddressRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Given("the following concessionaire exists in the database:")
    fun theFollowingConcessionaireExistsInTheDatabase(json: String) {
        val node = objectMapper.readTree(json)
        jdbcTemplate.update(
            """
            INSERT INTO concessionaires (id, name, code, region)
            VALUES (?::uuid, ?, ?, ?)
            ON CONFLICT (name) DO NOTHING
            """,
            node.get("id").asText(),
            node.get("name").asText(),
            node.get("code")?.asText(),
            node.get("region")?.asText()
        )
    }

    @Given("the following service exists in the database:")
    fun theFollowingServiceExistsInTheDatabase(json: String) {
        val service = objectMapper.readValue(json, Service::class.java)
        service.constructionAddress?.let { addressJpa.save(AddressEntity.from(it)) }
        service.generatingAddress?.let { addressJpa.save(AddressEntity.from(it)) }
        serviceJpa.save(ServiceEntity.from(service))
    }
}
