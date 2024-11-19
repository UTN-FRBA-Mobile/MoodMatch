package utn.frba.mobile.moodmatch.data.model

data class Book(
    val name: String,
    val autor: String,
    val classification: String,
    val image: String,
    val sinopsis: String,
    val score: Double
)