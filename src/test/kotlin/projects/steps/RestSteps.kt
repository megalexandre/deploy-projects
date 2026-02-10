package projects.steps

import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson

class RestSteps {

    companion object {
        var response: ResponseEntity<String>? = null
        var lastCreatedId: String? = null
    }

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @When("I POST the payload to {string} with body:")
    fun iPostThePayloadToWithBody(endpoint: String, jsonBody: String) {
        val request = HttpEntity(jsonBody, HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        })

        response = restTemplate.postForEntity(endpoint, request, String::class.java)

        // Extrair o ID da resposta se for 201
        if (response?.statusCode?.value() == 201) {
            response?.body?.let { body ->
                try {
                    val jsonNode = objectMapper.readTree(body)
                    lastCreatedId = jsonNode.get("id")?.asText()
                } catch (e: Exception) {
                    // Ignorar se não conseguir parsear
                }
            }
        }
    }

    @Then("the response status code should be {int}")
    fun theResponseStatusCodeShouldBe(expectedStatus: Int) {
        val actualStatus = response?.statusCode?.value()

        assertEquals(expectedStatus, actualStatus) {
            "Expected status code $expectedStatus but got $actualStatus. Response body: ${response?.body}"
        }
    }

    @Then("the response body should contain:")
    fun theResponseBodyShouldContain(expectedJson: String) {
        val actualJson = response?.body ?: throw AssertionError("Response body is null")

        assertThatJson(actualJson)
            .isEqualTo(expectedJson)
    }
}

