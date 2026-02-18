package projects.steps

import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.en.Given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import projects.resources.SpringDataUsersRepository
import projects.resources.persistence.UserEntity
import projects.web.auth.port.`in`.LoginRequest
import projects.web.auth.port.out.LoginResponse
import java.util.*

class AuthenticationSteps(
    private val jdbcTemplate: JdbcTemplate,
    private val passwordEncoder: BCryptPasswordEncoder
) {
    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var usersRepository: SpringDataUsersRepository

    @Given("a user exists with email {string} and password {string}")
    fun createUserWithCredentials(email: String, password: String) {
        val userId = UUID.randomUUID()
        val encodedPassword = passwordEncoder.encode(password)

        usersRepository.save(UserEntity(
            id = userId,
            email = email,
            password = encodedPassword,
            profile = "USER",
            name = "Test User",
            createdAt = Date().toInstant(),
            updatedAt = Date().toInstant()
        ))
    }

    @Given("I am authenticated")
    fun authenticateUser() {
        
        if (TestContext.token != null) {
            return
        }
        
        val userId = UUID.randomUUID()
        val email = "test@mail.com"
        val password = "password"
        val encodedPassword = passwordEncoder.encode(password)

        usersRepository.save(UserEntity(
            id = userId,
            email = email,
            password = encodedPassword,
            profile = "USER",
            name = "Test User",
            createdAt = Date().toInstant(),
            updatedAt = Date().toInstant()
        ))

        val response: ResponseEntity<LoginResponse> = restTemplate.postForEntity(
            "/auth/login",
            LoginRequest(email = email, password = password),
            LoginResponse::class.java
        )

        TestContext.token = response.body?.token
        TestContext.currentUserId = userId.toString()
    }

    @Given("I am not authenticated")
    fun clearAuthentication() {
        TestContext.token = null
        TestContext.currentUserId = null
    }
}
