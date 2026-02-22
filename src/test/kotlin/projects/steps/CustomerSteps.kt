package projects.steps

import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.en.Given
import org.springframework.beans.factory.annotation.Autowired
import projects.core.model.Customer
import projects.resources.SpringDataCustomerRepository
import projects.resources.persistence.CustomerEntity

class CustomerSteps {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var jpa: SpringDataCustomerRepository

    @Given("the following customer exists in the database:")
    fun theFollowingCustomerExistsInTheDatabase(json: String) {
        val customer = objectMapper.readValue(json, Customer::class.java)
        jpa.save(CustomerEntity.from(customer))
    }
}
