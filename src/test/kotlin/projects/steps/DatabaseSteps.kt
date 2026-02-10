package projects.steps

import io.cucumber.java.en.Then
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate

class DatabaseSteps {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Then("the id {string} should exist in table {string}")
    fun theIdShouldExistInTable(idParam: String, tableName: String) {
        // Se o ID for um placeholder, usar o último ID criado
        val id = if (idParam.contains("json-unit.ignore") || idParam.contains("\${")) {
            RestSteps.lastCreatedId ?: throw AssertionError("No ID was captured from the response")
        } else {
            idParam
        }

        val sql = "SELECT COUNT(*) FROM $tableName WHERE id = ?"
        val count = jdbcTemplate.queryForObject(sql, Int::class.java, id) ?: 0

        assertTrue(count > 0) {
            "Expected id '$id' to exist in table '$tableName', but it was not found"
        }
    }

    @Then("the id {string} should not exist in table {string}")
    fun theIdShouldNotExistInTable(idParam: String, tableName: String) {
        val id = if (idParam.contains("json-unit.ignore") || idParam.contains("\${")) {
            RestSteps.lastCreatedId ?: throw AssertionError("No ID was captured from the response")
        } else {
            idParam
        }

        val sql = "SELECT COUNT(*) FROM $tableName WHERE id = ?"
        val count = jdbcTemplate.queryForObject(sql, Int::class.java, id) ?: 0

        assertTrue(count == 0) {
            "Expected id '$id' to NOT exist in table '$tableName', but it was found"
        }
    }

    @Then("the table {string} should have {int} records")
    fun theTableShouldHaveRecords(tableName: String, expectedCount: Int) {
        val sql = "SELECT COUNT(*) FROM $tableName"
        val actualCount = jdbcTemplate.queryForObject(sql, Int::class.java) ?: 0

        assertTrue(actualCount == expectedCount) {
            "Expected table '$tableName' to have $expectedCount record(s), but found $actualCount"
        }
    }

}
