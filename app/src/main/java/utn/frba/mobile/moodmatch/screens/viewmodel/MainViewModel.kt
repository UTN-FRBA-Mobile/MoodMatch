package utn.frba.mobile.moodmatch.screens.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import utn.frba.mobile.moodmatch.common.Mood
import utn.frba.mobile.moodmatch.common.Platform
import utn.frba.mobile.moodmatch.common.Recommendation
import utn.frba.mobile.moodmatch.data.model.Activity
import utn.frba.mobile.moodmatch.data.model.Book
import utn.frba.mobile.moodmatch.data.model.Movie
import utn.frba.mobile.moodmatch.data.model.Series
import utn.frba.mobile.moodmatch.repository.APIMoodMatchRepository

class MainViewModel() : ViewModel() {
    private val repository = APIMoodMatchRepository()

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _recommendations = MutableStateFlow<List<Recommendation>>(emptyList())
    val recommendations: StateFlow<List<Recommendation>> = _recommendations.asStateFlow()

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books.asStateFlow()

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _recreationalActivities = MutableStateFlow<List<Activity>>(emptyList())
    val recreationalActivities: StateFlow<List<Activity>> = _recreationalActivities.asStateFlow()

    var selectedRecommendation by mutableStateOf<Recommendation?>(null)
        private set

    // Usamos suspend fun para esperar la llamada de la API
    suspend fun getRecommendations(mood: Mood) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val recommendations = repository.fetchRecommendations(mood)
                Log.d("MainViewModel", "Recommendations fetched: $recommendations")
                // Parseamos la respuesta a una lista de recomendaciones
                _recommendations.value = parseRecommendations(recommendations)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Función para parsear el Map<String, Any> a una lista de Recommendation
    fun parseRecommendations(response: Map<String, Any>): List<Recommendation> {
        val recommendations = mutableListOf<Recommendation>()
        Log.d("MainViewModel", "Recommendations to parse: $response")
        response.forEach { (key, value) ->
            when (key) {
                "books" -> {
                    val book = value as? Book
                    if (book != null) {
                        recommendations.add(
                            Recommendation(
                                title = book.name,
                                creator = book.autor,
                                image = book.image,
                                type = "Book",
                                score = book.score.toFloat(),
                                platform = Platform.NA,
                                sinopsis = book.sinopsis
                            )
                        )
                    }
                }
                "movies" -> {
                    val movie = value as? Movie
                    if (movie != null) {
                        recommendations.add(
                            Recommendation(
                                title = movie.name,
                                creator = movie.director,
                                image = movie.image,
                                type = "Movie",
                                score =  movie.score.toFloat(),
                                sinopsis = movie.sinopsis,
                                platform = movie.plataforma ?: Platform.NA
                            )
                        )
                    }
                }
                "series" -> {
                    val series = value as? Series
                    if (series != null) {
                        recommendations.add(
                            Recommendation(
                                title = series.name,
                                creator = series.director,
                                image = series.image,
                                type = "Series",
                                score = series.score.toFloat(),
                                sinopsis = series.sinopsis,
                                platform = series.plataforma ?: Platform.NA
                            )
                        )
                    }
                }
                "activities" -> {
                    val activity = value as? Activity
                    if (activity != null) {
                        recommendations.add(
                            Recommendation(
                                title = activity.name,
                                creator = activity.classification,
                                image = activity.image,
                                type = "Activity",
                                score = 7.0F,
                                sinopsis = activity.sinopsis,
                                platform = Platform.NA
                            )
                        )
                    }
                }
                else -> {
                    Log.w("MainViewModel", "Unhandled key: $key")
                }
            }
        }
        Log.d("MainViewModel", "Parsed recommendations: $recommendations")
        return recommendations
    }

    fun setRecommendation(recommendation: Recommendation) {
        selectedRecommendation = recommendation
    }

    fun getSeries(): List<Series> {
        var result: List<Series> = emptyList()
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    result = repository.fetchSeries()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return result

    }

    suspend fun getMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val movies = repository.fetchMovies()
                _movies.value = movies
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    suspend fun getBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val books = repository.fetchBooks()
                _books.value = books
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    suspend fun getRecreationalActivities() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val recreationalActivities = repository.fetchRecreationalActivities()
                _recreationalActivities.value = recreationalActivities
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}