package projects.core.usecase.users

import org.springframework.stereotype.Service
import projects.core.model.User
import projects.core.respository.UserRepository

@Service
class UserUpdateUseCase(
    private val userRepository: UserRepository
) {

    fun execute(user: User): User =
        userRepository.save(user)

}