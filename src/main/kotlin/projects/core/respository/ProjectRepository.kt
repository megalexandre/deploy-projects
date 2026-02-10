package projects.core.respository

import projects.core.model.Project

interface ProjectRepository {
    fun save(project: Project): Project
    fun delete(projectId: String)
    fun findAll(): List<Project>
    fun findById(projectId: String): Project?
}