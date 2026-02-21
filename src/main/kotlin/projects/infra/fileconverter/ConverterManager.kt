package projects.infra.fileconverter

import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartHttpServletRequest
import projects.web.projects.port.`in`.ProjectBatchCreateRequest

@Component
class ConverterManager(
    private val converters: List<FileConverter>
) {

    fun execute(request: MultipartHttpServletRequest): ProjectBatchCreateRequest {

        val file = request.getFile("file")
            ?: throw IllegalArgumentException("No file parameter found in multipart request")

        val fileName = file.originalFilename?.lowercase() ?: ""
        val fileExtension = fileName.substringAfterLast('.', "")

        val converter = converters.firstOrNull { converter ->
            converter.supports().any { extension ->
                fileExtension.equals(extension, ignoreCase = true)
            }
        } ?: throw IllegalArgumentException(
            "Unsupported file format: .$fileExtension. Supported formats: ${
                converters.flatMap { it.supports() }.joinToString(", ") { ".$it" }
            }"
        )

        return converter.execute(file)
    }

}