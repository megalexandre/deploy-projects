package projects.core.usecase.company

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import projects.core.model.Company
import projects.core.respository.CompanyRepository

@Service
class CompanyFindAllUseCase(
    private val repository: CompanyRepository,
) {

    private val logger = LoggerFactory.getLogger(CompanyFindAllUseCase::class.java)

    fun execute(): List<Company> =
        repository.findAll()

}