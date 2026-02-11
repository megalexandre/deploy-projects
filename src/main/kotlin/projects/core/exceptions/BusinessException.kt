package projects.core.exceptions

sealed class BusinessException(
    override val message: String,
    val field: String? = null
) : RuntimeException(message)

class DuplicateResourceException(
    message: String,
    field: String? = null
) : BusinessException(message, field)

class ResourceNotFoundException(
    message: String
) : BusinessException(message)

class InvalidCredentialsException(
    message: String = "Invalid email or password"
) : BusinessException(message)
