package utn.frba.mobile.moodmatch.data.model

data class LoginResponse(
    val msg: String,
    val name: String,
    val image: String,
    val error: String? = null
)