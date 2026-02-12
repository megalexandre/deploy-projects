package projects.web.auth.port.out

import org.springframework.security.core.GrantedAuthority
import projects.infra.security.CustomUserDetails

data class CustomUserDetailsResponse(
    val userId: String,
    val email: String,
    val authorities: Collection<GrantedAuthority> = emptyList()
)

fun CustomUserDetails.toResponse(): CustomUserDetailsResponse =
    CustomUserDetailsResponse(
        userId = this.userId,
        email = this.username,
        authorities = this.authorities
    )