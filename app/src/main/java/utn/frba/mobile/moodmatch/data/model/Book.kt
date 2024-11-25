package utn.frba.mobile.moodmatch.data.model

data class Book(
    override val name: String,
    val autor: String,
    override val classification: String,
    override val image: String,
    override val sinopsis: String,
    val score: Double
):Enterteinment