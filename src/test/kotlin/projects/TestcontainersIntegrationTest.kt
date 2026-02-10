package projects

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.jdbc.core.JdbcTemplate
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.assertNotNull

@SpringBootTest
@Testcontainers
@Import(TestcontainersConfiguration::class)
class TestcontainersIntegrationTest {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun `should connect to PostgreSQL testcontainer`() {
        val result = jdbcTemplate.queryForObject("SELECT version()", String::class.java)
        assertNotNull(result)
    }
}
