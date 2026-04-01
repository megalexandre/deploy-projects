package projects.steps

import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.en.Given
import org.springframework.beans.factory.annotation.Autowired
import projects.core.model.Files
import projects.infra.entity.FilesEntity
import projects.resources.SpringDataFilesRepository
import java.util.UUID

class FilesSteps {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var jpa: SpringDataFilesRepository

    @Given("the following file exists in the database:")
    fun theFollowingFileExistsInTheDatabase(json: String) {
        val file = objectMapper.readValue(json, Files::class.java)
        jpa.save(file.toEntity())
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

