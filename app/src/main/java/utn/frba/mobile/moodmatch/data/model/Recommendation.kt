package utn.frba.mobile.moodmatch.data.model

data class RecommendationResponse(
    val book: Book,
    val movie: Movie,
    val series: Series,
    val activity: Activity
)
