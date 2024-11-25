package utn.frba.mobile.moodmatch.repository

import android.util.Log
import utn.frba.mobile.moodmatch.common.Mood
import utn.frba.mobile.moodmatch.data.model.*
import utn.frba.mobile.moodmatch.data.network.RetrofitServiceFactory

class APIMoodMatchRepository {
    private val apiService = RetrofitServiceFactory.api

    suspend fun fetchRecommendations(mood: Mood): Map<String, Any> {
        val response = apiService.getRecommendations(mood.name)
        return mapOf(
            "books" to response.book,
            "movies" to response.movie,
            "series" to response.series,
            "activities" to response.activity
        )
    }

    suspend fun fetchRecreationalActivities(): List<Activity> {
        return apiService.getRecreationalActivities()
    }

    suspend fun fetchBooks(): List<Book> {
        Log.d("1 NAV CONTROLLER", apiService.getBooks().toString())
        return apiService.getBooks()
    }

    suspend fun fetchMovies(): List<Movie> {
        return apiService.getMovies()
    }

    suspend fun fetchSeries(): List<Series> {
        return apiService.getSeries()
    }
}