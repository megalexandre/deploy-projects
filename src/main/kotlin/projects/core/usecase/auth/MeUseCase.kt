package projects.core.usecase.auth

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import projects.infra.security.JwtService

@Service
class MeUseCase (
    private val jwtService: JwtService
){

    fun execute(principal: String, credentials: String): String {
        return "jwtService.generateToken(userDetails)"
    }
}