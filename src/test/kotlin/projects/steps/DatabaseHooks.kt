package projects.steps

import io.cucumber.java.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import software.amazon.awssdk.services.s3.S3Client

class DatabaseHooks {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    private lateinit var s3Client: S3Client

    @Value("\${aws.s3.bucket-name}")
    private lateinit var bucketName: String

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

        // Clean S3 bucket
        cleanS3Bucket()

        TestContext.token = null
        TestContext.currentUserId = null
    }

    private fun cleanS3Bucket() {
        try {
            // Ensure bucket exists
            try {
                s3Client.headBucket { it.bucket(bucketName) }
            } catch (e: Exception) {
                // Create bucket if it doesn't exist
                s3Client.createBucket { it.bucket(bucketName) }
                return
            }

            // List and delete all objects in the bucket
            val listObjectsResponse = s3Client.listObjectsV2 { it.bucket(bucketName) }

            listObjectsResponse.contents().forEach { s3Object ->
                s3Client.deleteObject { builder ->
                    builder.bucket(bucketName).key(s3Object.key())
                }
            }
        } catch (e: Exception) {
            // Ignore S3 cleanup errors in tests
            println("Warning: Failed to clean S3 bucket: ${e.message}")
        }
    }
}
