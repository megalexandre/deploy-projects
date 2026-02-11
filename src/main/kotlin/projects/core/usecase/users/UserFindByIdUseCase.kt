package projects.core.usecase.users

import org.springframework.stereotype.Service
import projects.core.model.User
import projects.core.respository.UserRepository

@Service
class UserFindByIdUseCase(
    private val repository: UserRepository
) {

    fun execute(id: String): User? = repository.findById(id)

}