package utn.frba.mobile.moodmatch.repository

import utn.frba.mobile.moodmatch.data.network.RetrofitServiceFactory

class RecommendationRepository {
    private val api = RetrofitServiceFactory.api

    suspend fun fetchRecommendations(type: String, classification: String): List<Any> {
        return when (type) {
            "movie" -> api.getMovies(classification).result["movies"] ?: emptyList()
            "books" -> api.getBooks(classification).result["books"] ?: emptyList()
            "serie" -> api.getSeries(classification).result["series"] ?: emptyList()
            "activity" -> api.getRecreationalActivities(classification).result["activities"] ?: emptyList()
            else -> emptyList()
        }
    }
}