package projects.core.usecase.users

import org.springframework.stereotype.Service
import projects.core.model.User
import projects.core.respository.UserRepository

@Service
class UserFindAllUseCase(
    private val repository: UserRepository
) {

    fun execute(): List<User> = repository.findAll()

}