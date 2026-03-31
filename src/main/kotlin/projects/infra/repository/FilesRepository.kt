package projects.infra.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import projects.infra.entity.FilesEntity
import java.util.UUID

@Repository
interface FilesRepository : JpaRepository<FilesEntity, UUID> {
}

