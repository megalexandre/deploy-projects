package projects.core.usecase.auth

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import projects.core.model.User
import projects.core.respository.UserRepository

@Service
class UserCreateUseCase(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun execute(user: User): User {
        val userWithEncodedPassword = user.copy(password = passwordEncoder.encode(user.password))
        return userRepository.save(userWithEncodedPassword)
    }

}