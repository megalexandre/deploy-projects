package projects.steps

import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.en.Given
import org.springframework.beans.factory.annotation.Autowired
import projects.core.model.Project
import projects.resources.SpringDataProjectRepository
import projects.resources.persistence.ProjectEntity

class ProjectSteps {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var jpa: SpringDataProjectRepository

    @Given("the following project exists in the database:")
    fun theFollowingProjectExistsInTheDatabase(json: String) {
        val project = objectMapper.readValue(json, Project::class.java)
        jpa.save(ProjectEntity.from(project))
    }
}
