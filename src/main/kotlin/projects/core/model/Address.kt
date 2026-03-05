package projects.core.model

data class Address (
    val id: String,
    val place: String,
    val cep: String,
    val number: String,
    val address: String,
    val neighborhood: String,
    val city: String,
    val state: String,

    val complement: String?,
    val link: String?
)

