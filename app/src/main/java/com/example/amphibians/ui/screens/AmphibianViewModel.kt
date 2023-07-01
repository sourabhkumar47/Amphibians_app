package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * UI state for the [HomeScreen].
 */
sealed interface AmphibialUiState {
    data class Success(val photos: List<AmphibianPhoto>) : AmphibialUiState
    object Loading : AmphibialUiState
    object Error : AmphibialUiState
}

class AmphibianViewModel() : ViewModel() {

    //stores the current state of the UI
    var amphibialUiState: AmphibialUiState by mutableStateOf(AmphibialUiState.Loading)
        private set

    fun getAmphibiansDetails(){
        viewModelScope.launch {
            amphibialUiState = try {
                amphibialUiState.Success()
            }
        }
    }
}
