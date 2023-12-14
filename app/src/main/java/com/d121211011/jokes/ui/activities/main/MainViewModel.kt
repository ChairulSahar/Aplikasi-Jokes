package com.d121211011.jokes.ui.activities.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211011.jokes.MyApplication
import com.d121211011.jokes.data.models.Jokes
import com.d121211011.jokes.data.repository.JokesRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val jokes: List<Jokes>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val jokesRepository: JokesRepository): ViewModel() {

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getJokes() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = jokesRepository.getJokes(
            )
            Log.d("MainViewModel", "getJokes: ${result.jokes?.size}")
            mainUiState = MainUiState.Success(result.jokes.orEmpty())
        } catch (e: IOException) {
            Log.d("MainViewMode", "getJokes error: ${e.message}")
            mainUiState = MainUiState.Error
        }
    }

    init {
        getJokes()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MyApplication)
                val jokesRepository = application.container.jokesRepository
                MainViewModel(jokesRepository)
            }
        }
    }

}