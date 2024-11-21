package utn.frba.mobile.moodmatch.data.model

data class Movie(
    override val name: String,
    val plataforma: String,
    override val classification: String,
    override val image: String,
    override val sinopsis: String,
    val score: Double,
    val director: String,
):Enterteinment