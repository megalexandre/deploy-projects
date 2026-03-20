package projects.core.usecase.company

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import projects.core.model.Company
import projects.core.respository.CompanyRepository

@Service
class CompanyFindByIdUseCase(
    private val repository: CompanyRepository,
) {

    private val logger = LoggerFactory.getLogger(CompanyFindByIdUseCase::class.java)

    fun execute(id: String): Company? = repository.findById(id)

}