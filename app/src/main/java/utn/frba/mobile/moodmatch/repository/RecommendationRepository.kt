package utn.frba.mobile.moodmatch.repository

import utn.frba.mobile.moodmatch.data.network.RetrofitServiceFactory

class RecommendationRepository {
    private val api = RetrofitServiceFactory.api

    suspend fun fetchRecommendations(type: String, classification: String): List<Any> {
        return when (type) {
            "movies" -> {
                val result = api.getMovies(classification).result[classification]
                result ?: emptyList()
            }
            "books" -> api.getBooks(classification).result[classification] ?: emptyList()
            "series" -> api.getSeries(classification).result[classification] ?: emptyList()
            "activity" -> api.getRecreationalActivities(classification).result[classification] ?: emptyList()
            else -> emptyList()
        }
    }
}