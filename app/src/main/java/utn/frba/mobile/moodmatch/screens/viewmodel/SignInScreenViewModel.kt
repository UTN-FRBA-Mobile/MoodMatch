package utn.frba.mobile.moodmatch.screens.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import utn.frba.mobile.moodmatch.repository.UserRepository

class SignInScreenViewModel(private val userRepository: UserRepository) : ViewModel() {

    var loginResult by mutableStateOf<Pair<Boolean, String>?>(null)
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                userRepository.login(email, password)
            }
            loginResult = if (result.first) {
                Pair(true, "Login exitoso: ${result.second}")
            } else {
                Pair(false, "Error: ${result.second}")
            }
        }
    }
}