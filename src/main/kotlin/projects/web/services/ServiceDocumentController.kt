package projects.web.services

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import projects.core.usecase.service.ServiceDocumentCreateUseCase
import projects.core.usecase.service.ServiceDocumentDeleteUseCase
import projects.core.usecase.service.ServiceDocumentFindAllUseCase
import projects.web.services.port.out.ServiceDocumentResponse
import projects.web.services.port.out.toResponse

@RestController
@RequestMapping("/services/{serviceId}/documents")
class ServiceDocumentController(
    private val create: ServiceDocumentCreateUseCase,
    private val findAll: ServiceDocumentFindAllUseCase,
    private val delete: ServiceDocumentDeleteUseCase,
) {
    private val log = LoggerFactory.getLogger(ServiceDocumentController::class.java)

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun upload(
        @PathVariable serviceId: String,
        @RequestParam("type") type: String,
        @RequestParam("file") file: MultipartFile,
    ): ServiceDocumentResponse {
        log.info("Uploading document for service $serviceId")
        return create.execute(serviceId, type, file).toResponse()
    }

    @GetMapping
    fun list(@PathVariable serviceId: String): List<ServiceDocumentResponse> {
        log.info("Listing documents for service $serviceId")
        return findAll.execute(serviceId).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remove(@PathVariable serviceId: String, @PathVariable id: String) {
        log.info("Deleting document $id from service $serviceId")
        delete.execute(id)
    }
}
