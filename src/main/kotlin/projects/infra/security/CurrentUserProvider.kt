package projects.infra.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class CurrentUserProvider {

    fun getCurrentUser(): CustomUserDetails? =
        SecurityContextHolder.getContext().authentication?.principal as? CustomUserDetails

}