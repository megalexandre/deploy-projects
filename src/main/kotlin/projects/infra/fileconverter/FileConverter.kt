package projects.infra.fileconverter

import org.springframework.web.multipart.MultipartFile
import projects.web.projects.port.`in`.ProjectBatchCreateRequest

interface FileConverter {

    /**
     * Returns the list of file extensions supported by this converter
     */
    fun supports(): List<String>

    /**
     * Executes the conversion from file to ProjectBatchCreateRequest
     */
    fun execute(file: MultipartFile): ProjectBatchCreateRequest
}
