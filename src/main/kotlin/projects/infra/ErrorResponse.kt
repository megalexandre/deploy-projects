package projects.infra

data class ErrorResponse(
    val status: Int,
    val message: String,
    val errors: Map<String, String?>
)