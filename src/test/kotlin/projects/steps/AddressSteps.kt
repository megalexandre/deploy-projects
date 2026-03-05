package projects.steps

import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.en.Given
import org.springframework.beans.factory.annotation.Autowired
import projects.core.model.Address
import projects.resources.SpringDataAddressRepository
import projects.resources.persistence.AddressEntity

class AddressSteps {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var jpa: SpringDataAddressRepository

    @Given("the following address exists in the database:")
    fun theFollowingProjectExistsInTheDatabase(json: String) {
        val address = objectMapper.readValue(json, Address::class.java)
        jpa.save(AddressEntity.from(address))
    }
}
