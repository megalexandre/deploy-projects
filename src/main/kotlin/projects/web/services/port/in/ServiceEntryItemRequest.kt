package projects.web.services.port.`in`

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import projects.commons.Id
import projects.core.model.ServiceEntryItem

class ServiceEntryItemRequest(

    @field:NotNull
    @field:NotBlank
    val connectionType: String?,

    @field:NotNull
    @field:NotBlank
    val classification: String?,

    @field:NotNull
    @field:Min(1)
    val quantity: Int?,

    @field:NotNull
    @field:NotBlank
    val circuitBreaker: String?,
) {
    fun toDomain() = ServiceEntryItem(
        id = Id.random(),
        connectionType = connectionType!!,
        classification = classification!!,
        quantity = quantity!!,
        circuitBreaker = circuitBreaker!!
    )
}
