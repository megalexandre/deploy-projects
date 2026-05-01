package projects.core.model

data class Company(
    val id: String,
    val name: String,
    val acronym: String? = null,
    val code: String? = null,
    val region: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val active: Boolean = true,
)
