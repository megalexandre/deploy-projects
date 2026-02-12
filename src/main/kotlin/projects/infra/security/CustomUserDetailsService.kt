package projects.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import projects.core.respository.UserRepository

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): CustomUserDetails {
        val user = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("User not found: $email")

        return CustomUserDetails(
            userId = user.id,
            user.email,
            user.password,
            getAuthorities(user.profile)
        )
    }

    private fun getAuthorities(profile: String): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_$profile"))
    }
}
