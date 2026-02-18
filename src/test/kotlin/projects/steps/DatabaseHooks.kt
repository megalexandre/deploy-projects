package projects.steps

import io.cucumber.java.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate

class DatabaseHooks {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Before
    fun cleanDatabase() {
        jdbcTemplate.execute("TRUNCATE TABLE projects CASCADE")
        jdbcTemplate.execute("TRUNCATE TABLE users CASCADE")
        TestContext.token = null
        TestContext.currentUserId = null
    }
}
