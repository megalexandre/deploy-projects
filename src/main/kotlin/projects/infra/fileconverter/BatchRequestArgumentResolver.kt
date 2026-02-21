package projects.infra.fileconverter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Validator
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.multipart.MultipartHttpServletRequest
import projects.web.projects.port.`in`.ProjectBatchCreateRequest

@Component
class BatchRequestArgumentResolver(
    private val objectMapper: ObjectMapper,
    private val converterManager: ConverterManager,
    private val validator: Validator
) : HandlerMethodArgumentResolver {

    private val logger = LoggerFactory.getLogger(BatchRequestArgumentResolver::class.java)

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == ProjectBatchCreateRequest::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): ProjectBatchCreateRequest {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java)
            ?: throw IllegalStateException("Unable to get HttpServletRequest")

        val result = when {
            request.contentType?.contains("multipart/form-data") == true -> {

                request as? MultipartHttpServletRequest
                    ?: throw IllegalArgumentException("Expected MultipartHttpServletRequest")

                converterManager.execute(request)
            }

            request.contentType?.contains("application/json") == true -> {
                logger.info("Processing application/json request")
                objectMapper.readValue(request.inputStream, ProjectBatchCreateRequest::class.java)
            }

            else -> {
                throw IllegalArgumentException("Unsupported Content-Type: ${request.contentType}")
            }
        }

        // Validate the object if @Valid is present
        if (parameter.hasParameterAnnotation(jakarta.validation.Valid::class.java)) {
            val violations = validator.validate(result)
            if (violations.isNotEmpty()) {
                val bindingResult = org.springframework.validation.BeanPropertyBindingResult(result, "batchRequest")
                violations.forEach { violation ->
                    bindingResult.rejectValue(
                        violation.propertyPath.toString(),
                        violation.constraintDescriptor.annotation.toString(),
                        violation.message
                    )
                }
                throw MethodArgumentNotValidException(parameter, bindingResult)
            }
        }

        return result
    }

}
