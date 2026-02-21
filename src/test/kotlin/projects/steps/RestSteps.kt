package projects.steps

import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import projects.helpers.TestFileGenerator
import java.io.File

class RestSteps {

    companion object {
        var response: ResponseEntity<String>? = null
        var lastCreatedId: String? = null
    }

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @When("I GET {string}")
    fun iGet(endpoint: String) {

        val entity = HttpEntity<Unit>(HttpHeaders().apply {
            TestContext.token?.let { setBearerAuth(it) }
        })

        response = restTemplate.exchange(
            endpoint,
            HttpMethod.GET,
            entity,
            String::class.java
        )
    }


    @When("I POST the payload to {string} with body:")
    fun iPostThePayloadToWithBody(endpoint: String, jsonBody: String) {
        val request = HttpEntity(jsonBody, HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            TestContext.token?.let { setBearerAuth(it) }
        })

        response = restTemplate.postForEntity(endpoint, request, String::class.java)

        if (response?.statusCode?.value() == 201) {
            response?.body?.let { body ->
                try {
                    val jsonNode = objectMapper.readTree(body)
                    lastCreatedId = jsonNode.get("id")?.asText()
                } catch (_: Exception) {
                }
            }
        }
    }

    @When("I POST to {string} with file {string}")
    fun iPostWithFile(endpoint: String, fileName: String) {
        val file: File = when {
            fileName.endsWith(".xlsx") || fileName.endsWith(".xls") -> {
                TestFileGenerator.createTestXlsxFile(fileName)
            }
            else -> {
                // Tenta carregar do classpath
                ClassPathResource("test-files/$fileName").file
            }
        }

        val body = LinkedMultiValueMap<String, Any>()
        body.add("file", FileSystemResource(file))

        val headers = HttpHeaders().apply {
            contentType = MediaType.MULTIPART_FORM_DATA
            TestContext.token?.let { setBearerAuth(it) }
        }

        val request = HttpEntity(body, headers)

        response = restTemplate.postForEntity(endpoint, request, String::class.java)
    }

    @When("I POST to {string} with file {string} with content:")
    fun iPostWithFileWithContent(endpoint: String, fileName: String, content: String) {
        val file = when {
            fileName.endsWith(".xlsx") -> {
                TestFileGenerator.createXlsxFromContent(fileName, content)
            }
            fileName.endsWith(".xls") -> {
                TestFileGenerator.createXlsFromContent(fileName, content)
            }
            fileName.endsWith(".csv") -> {
                TestFileGenerator.createCsvFromContent(fileName, content)
            }
            else -> throw IllegalArgumentException("Unsupported file format: $fileName")
        }

        val body = LinkedMultiValueMap<String, Any>()
        body.add("file", FileSystemResource(file))

        val headers = HttpHeaders().apply {
            contentType = MediaType.MULTIPART_FORM_DATA
            TestContext.token?.let { setBearerAuth(it) }
        }

        val request = HttpEntity(body, headers)

        response = restTemplate.postForEntity(endpoint, request, String::class.java)

        // Cleanup temporary file
        file.delete()
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

