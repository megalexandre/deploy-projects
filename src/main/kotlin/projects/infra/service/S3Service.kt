package projects.infra.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.core.sync.ResponseTransformer
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest

@Service
class S3Service(
    private val s3Client: S3Client,
    @Value("\${aws.s3.bucket-name}") private val bucketName: String
) {

    fun uploadFile(file: MultipartFile, key: String): String {
        val putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .contentType(file.contentType ?: "application/octet-stream")
            .build()

        s3Client.putObject(
            putObjectRequest,
            RequestBody.fromInputStream(file.inputStream, file.size)
        )

        return "https://${bucketName}.s3.amazonaws.com/${key}"
    }

    fun deleteFile(key: String) {
        s3Client.deleteObject { builder ->
            builder.bucket(bucketName).key(key)
        }
    }

    fun downloadFile(key: String): ByteArray {
        val getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build()

        return s3Client.getObject(getObjectRequest, ResponseTransformer.toBytes()).asByteArray()
    }
}

