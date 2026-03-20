package projects.steps

import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.en.Given
import org.springframework.beans.factory.annotation.Autowired
import projects.resources.SpringDataCompanyRepository
import projects.resources.persistence.CompanyEntity

class CompanySteps {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var jpa: SpringDataCompanyRepository

    @Given("the following company exists in the database:")
    fun theFollowingCompanyExistsInTheDatabase(json: String) {
        val company = objectMapper.readValue(json, CompanyEntity::class.java)
        jpa.save(company)
    }
}
