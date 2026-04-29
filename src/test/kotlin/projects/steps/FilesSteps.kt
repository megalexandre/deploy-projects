package projects.steps

import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import projects.core.model.Files
import projects.infra.entity.FilesEntity
import projects.resources.SpringDataFilesRepository
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.HeadObjectRequest
import software.amazon.awssdk.services.s3.model.NoSuchKeyException
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat

class FilesSteps {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var jpa: SpringDataFilesRepository

    @Autowired
    private lateinit var s3Client: S3Client

    @Value("\${aws.s3.bucket-name}")
    private lateinit var bucketName: String

    @Given("the following file exists in the database:")
    fun theFollowingFileExistsInTheDatabase(json: String) {
        val file = objectMapper.readValue(json, Files::class.java)
        jpa.save(file.toEntity())
    }

    @Given("the file {string} exists in S3")
    fun theFileExistsInS3(fileName: String) {
        ensureBucketExists()

        val key = if (fileName.contains("/")) {
            fileName
        } else {
            "items/019ca71b-3183-7a8b-8f71-e44a327a7846/$fileName"
        }

        val putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .contentType("application/pdf")
            .build()

        val dummyContent = "This is a test file content for $fileName"
        s3Client.putObject(putObjectRequest, RequestBody.fromString(dummyContent))
    }

    @Then("the file {string} should not exist in S3")
    fun theFileShouldNotExistInS3(key: String) {
        try {
            val headObjectRequest = HeadObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build()

            s3Client.headObject(headObjectRequest)

            // If we get here, the file exists (which is bad)
            throw AssertionError("File $key should not exist in S3, but it does")
        } catch (e: NoSuchKeyException) {
            // Expected - file doesn't exist
            assertThat(true).isTrue()
        }
    }

    private fun ensureBucketExists() {
        try {
            s3Client.headBucket { it.bucket(bucketName) }
        } catch (e: Exception) {
            // Create bucket if it doesn't exist
            s3Client.createBucket { it.bucket(bucketName) }
        }
    }

    private fun Files.toEntity(): FilesEntity {
        return FilesEntity(
            id = UUID.fromString(this.id),
            itemId = UUID.fromString(this.itemId),
            filename = this.fileName,
            urlS3 = this.urlS3,
            size = this.size,
            createdAt = this.createdAt ?: java.time.Instant.now(),
            updatedAt = java.time.Instant.now()
        )
    }
}

