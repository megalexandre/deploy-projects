package projects.infra

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import projects.core.exceptions.DuplicateResourceException
import projects.core.exceptions.InvalidCredentialsException
import projects.core.exceptions.ResourceNotFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ErrorResponse {
        val errors = mutableMapOf<String, String?>()
        
        ex.bindingResult.fieldErrors.forEach { error ->
            errors[error.field] = error.defaultMessage
        }

        return ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = "Validation Failed",
            errors = errors
        )
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateResourceException::class)
    fun handleDuplicateResource(ex: DuplicateResourceException): ErrorResponse {
        val errors = if (ex.field != null) {
            mapOf(ex.field to ex.message)
        } else {
            emptyMap()
        }

        return ErrorResponse(
            status = HttpStatus.CONFLICT.value(),
            message = ex.message,
            errors = errors
        )
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFound(ex: ResourceNotFoundException): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message,
            errors = emptyMap()
        )
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCredentials(ex: InvalidCredentialsException): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.UNAUTHORIZED.value(),
            message = ex.message,
            errors = emptyMap()
        )
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolation(ex: DataIntegrityViolationException): ErrorResponse {
        val message = ex.rootCause?.message ?: ex.message ?: ""

        return when {
            message.contains("users_email_key", ignoreCase = true) -> {
                ErrorResponse(
                    status = HttpStatus.CONFLICT.value(),
                    message = "Email already exists",
                    errors = mapOf("email" to "This email is already registered")
                )
            }
            message.contains("users_name_key", ignoreCase = true) -> {
                ErrorResponse(
                    status = HttpStatus.CONFLICT.value(),
                    message = "Name already exists",
                    errors = mapOf("name" to "This name is already in use")
                )
            }
            message.contains("duplicate key", ignoreCase = true) -> {
                ErrorResponse(
                    status = HttpStatus.CONFLICT.value(),
                    message = "Duplicate resource",
                    errors = mapOf("error" to "A resource with this unique value already exists")
                )
            }
            else -> {
                ErrorResponse(
                    status = HttpStatus.CONFLICT.value(),
                    message = "Data integrity violation",
                    errors = mapOf("error" to "Unable to process the request due to data constraints")
                )
            }
        }
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentials(ex: BadCredentialsException): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.UNAUTHORIZED.value(),
            message = "Usuário inexistente ou senha inválida",
            errors = emptyMap()
        )
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ErrorResponse {
        // Log the exception for debugging
        ex.printStackTrace()

        return ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "An unexpected error occurred",
            errors = emptyMap()
        )
    }
}



