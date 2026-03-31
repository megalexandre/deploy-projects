package projects.infra.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.S3Configuration as AwsS3Configuration
import java.net.URI

@Configuration
class S3Configuration {

    @Value("\${aws.s3.access-key}")
    private lateinit var accessKey: String

    @Value("\${aws.s3.secret-key}")
    private lateinit var secretKey: String

    @Value("\${aws.s3.region}")
    private lateinit var region: String

    @Value("\${aws.s3.endpoint:}")
    private var endpoint: String? = null

    @Bean
    fun s3Client(): S3Client {
        val credentials = AwsBasicCredentials.create(accessKey, secretKey)

        val builder = S3Client.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(credentials))

        // Se endpoint estiver configurado, usar ele (LocalStack)
        if (!endpoint.isNullOrBlank()) {
            builder
                .endpointOverride(URI.create(endpoint!!))
                .serviceConfiguration(
                    AwsS3Configuration.builder()
                        .pathStyleAccessEnabled(true)
                        .build()
                )
        }

        return builder.build()
    }
}

