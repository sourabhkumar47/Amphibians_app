package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibianPhotosApplication
import com.example.amphibians.data.AmphibianPhotoRepository
import com.example.amphibians.network.AmphibianPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the [HomeScreen].
 */
sealed interface AmphibianUiState {
    data class Success(val photos: List<AmphibianPhoto>) : AmphibianUiState
    object Loading : AmphibianUiState
    object Error : AmphibianUiState
}

class AmphibianViewModel(private val amphibianPhotoRepository: AmphibianPhotoRepository) :
    ViewModel() {

    //stores the current state of the UI
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set


    /**
     * Call [getAmphibiansDetails()] on init so we can display status immediately.
     */
    init {
        getAmphibiansDetails()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [AmphibianPhoto] [List] [MutableList].
     */
    fun getAmphibiansDetails() {
        viewModelScope.launch {
            amphibianUiState = try {
                AmphibianUiState.Success(amphibianPhotoRepository.getAmphibianDetails())
            } catch (e: IOException) {
                AmphibianUiState.Error
            } catch (e: HttpException) {
                AmphibianUiState.Error
            }
        }
    }

    /**
     * Factory for [AmphibianViewModel] that takes [AmphibianPhotoRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibianPhotosApplication)
                val amphibianPhotoRepository = application.container.amphibianPhotoRepository
                AmphibianViewModel(amphibianPhotoRepository = amphibianPhotoRepository)
            }
        }
    }
}
