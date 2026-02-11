package projects.web.auth.port.out

data class LoginResponse(
    val token: String,
    val type: String = "Bearer",
    val expiresIn: Long = 86400000
)