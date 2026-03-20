package projects.core.usecase.company

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import projects.core.respository.CompanyRepository

@Service
class CompanyDeleteUseCase(
    private val repository: CompanyRepository,
) {

    private val logger = LoggerFactory.getLogger(CompanyDeleteUseCase::class.java)

    fun execute(id: String){
        repository.delete(id)
    }

}