package projects.core.usecase.files

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import projects.core.model.Files
import projects.core.respository.FilesRepository

@Service
class FilesListUseCase(
    private val filesRepository: FilesRepository
) {

    @Transactional(readOnly = true)
    fun execute(itemId: String): List<Files> {
        return filesRepository.findAllByItemId(itemId)
    }

}

