package projects.steps

import io.cucumber.java.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate

class DatabaseHooks {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Before
    fun cleanDatabase() {
        val tables = jdbcTemplate.queryForList(
            """
            SELECT tablename 
            FROM pg_tables 
            WHERE schemaname = 'public' 
            AND tablename != 'flyway_schema_history'
            """,
            String::class.java
        )

        tables.forEach { table ->
            jdbcTemplate.execute("TRUNCATE TABLE $table CASCADE")
        }

        TestContext.token = null
        TestContext.currentUserId = null
    }
}
