package projects.web.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import projects.core.usecase.files.FilesCreateUseCase
import projects.core.usecase.files.FilesDeleteByIdUseCase
import projects.core.usecase.files.FilesDeleteByItemIdUseCase
import projects.core.usecase.files.FilesDownloadUseCase
import projects.core.usecase.files.FilesListUseCase
import projects.web.controller.dto.FilesResponse
import projects.web.controller.dto.toResponse

@RestController
@RequestMapping("/api/v1/file")
class FilesController(
    private val filesUseCase: FilesCreateUseCase,
    private val filesListUseCase: FilesListUseCase,
    private val filesDownloadUseCase: FilesDownloadUseCase,
    private val filesDeleteByIdUseCase: FilesDeleteByIdUseCase,
    private val filesDeleteByItemIdUseCase: FilesDeleteByItemIdUseCase
) {

    @PostMapping(
        "/upload",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun uploadFiles(
        @RequestParam("id") id: String,
        @RequestParam("files") files: List<MultipartFile>
    ): List<FilesResponse> {
        return filesUseCase.execute(id, files).toResponse()
    }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listFiles(@PathVariable id: String): ResponseEntity<List<FilesResponse>> {
        val files = filesListUseCase.execute(id)
        return if (files.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(files.toResponse())
        }
    }

    @GetMapping("/download/{fileId}")
    fun downloadFile(@PathVariable fileId: String): ResponseEntity<ByteArray> {
        val fileDownload = filesDownloadUseCase.execute(fileId)
            ?: return ResponseEntity.notFound().build()

        val headers = HttpHeaders().apply {
            contentDisposition = org.springframework.http.ContentDisposition
                .attachment()
                .filename(fileDownload.fileName)
                .build()
            contentType = MediaType.APPLICATION_OCTET_STREAM
        }

        return ResponseEntity.ok()
            .headers(headers)
            .body(fileDownload.content)
    }

    @DeleteMapping("/delete/{fileId}")
    fun deleteFileById(@PathVariable fileId: String): ResponseEntity<Void> {
        val deleted = filesDeleteByIdUseCase.execute(fileId)
        return if (deleted) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/delete/item/{itemId}")
    fun deleteFilesByItemId(@PathVariable itemId: String): ResponseEntity<Map<String, Int>> {
        val deletedCount = filesDeleteByItemIdUseCase.execute(itemId)
        return if (deletedCount > 0) {
            ResponseEntity.ok(mapOf("deletedCount" to deletedCount))
        } else {
            ResponseEntity.noContent().build()
        }
    }

}

