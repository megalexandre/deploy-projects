package projects.web.services.port.`in`

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import projects.commons.Id
import projects.core.model.Apportionment

class ApportionmentRequest(

    @field:NotNull
    @field:NotBlank
    val consumerUnit: String?,

    @field:NotNull
    @field:NotBlank
    val address: String?,

    @field:JsonProperty("class")
    @field:NotNull
    @field:NotBlank
    val classification: String?,

    @field:NotNull
    @field:Min(0)
    @field:Max(100)
    val percentage: Int?,
) {
    fun toDomain() = Apportionment(
        id = Id.random(),
        consumerUnit = consumerUnit!!,
        address = address!!,
        classification = classification!!,
        percentage = percentage!!
    )
}
