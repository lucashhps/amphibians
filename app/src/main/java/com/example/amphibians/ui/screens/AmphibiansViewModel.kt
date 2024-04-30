package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.amphibians.models.Amphibian
import com.example.amphibians.data.AmphibiansRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState {

    data class Success(val amphibians : List<Amphibian>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState


}

class AmphibiansViewModel(
    private val amphibiansRepository : AmphibiansRepository
) : ViewModel() {

    var amphibiansUiState : AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set


    // gets uistate and amphibians
    fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading
            amphibiansUiState = try {
                AmphibiansUiState.Success(amphibiansRepository.getAmphibians())
            } catch (e : IOException) {
                AmphibiansUiState.Error
            } catch (e : HttpException) {
                AmphibiansUiState.Error
            }
        }
    }

}