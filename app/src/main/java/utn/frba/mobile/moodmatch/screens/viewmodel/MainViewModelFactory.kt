package utn.frba.mobile.moodmatch.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory : ViewModelProvider.Factory {
    private var mainViewModel: MainViewModel? = null

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            if (mainViewModel == null) {
                mainViewModel = MainViewModel()
            }
            return mainViewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

