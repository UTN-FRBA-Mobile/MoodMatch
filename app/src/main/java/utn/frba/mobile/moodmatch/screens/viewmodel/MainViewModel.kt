package utn.frba.mobile.moodmatch.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import utn.frba.mobile.moodmatch.common.Mood
import utn.frba.mobile.moodmatch.data.model.*
import utn.frba.mobile.moodmatch.repository.APIMoodMatchRepository

class MainViewModel : ViewModel() {
    private val repository = APIMoodMatchRepository()

    fun getRecommendations(mood: Mood): Map<String, Any> {
        var result = mutableMapOf<String, Any>()
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val recommendation = repository.fetchRecommendations(mood)
                    result = recommendation as MutableMap<String, Any>
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return result

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

    fun getMovies(): List<Movie> {
        var result: List<Movie> = emptyList()
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    result = repository.fetchMovies()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        return result
    }

    fun getBooks(): List<Book> {
        var result: List<Book> = emptyList()
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    result = repository.fetchBooks()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        return result
    }

    fun getRecreationalActivities(): List<Activity> {
        var result: List<Activity> = emptyList()
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    result = repository.fetchRecreationalActivities()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return result
    }
}