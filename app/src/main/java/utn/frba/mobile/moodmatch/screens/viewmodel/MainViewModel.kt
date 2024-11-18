package utn.frba.mobile.moodmatch.screens.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import utn.frba.mobile.moodmatch.repository.RecommendationRepository

class MainViewModel : ViewModel() {
    private val repository = RecommendationRepository()

    fun getRecommendations(type: String, classification: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val recommendation = repository.fetchRecommendations(type, classification)
                println("Resultado de la API: $recommendation")


                recommendation.forEach{
                    println(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}