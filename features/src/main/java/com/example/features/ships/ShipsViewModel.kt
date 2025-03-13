package com.example.features.ships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.data.ShipsResponseItem
import com.example.network.model.repository.ShipsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShipsViewModel(private val repository: ShipsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(shipsUiState())

    val uiState = _uiState.asStateFlow()

    fun fetchShips(){
        viewModelScope.launch {
            val response = repository.getShipsInfo()
            if (response.isEmpty()){
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }else{
                _uiState.update {
                    it.copy(
                        ships = response,
                        isLoading = false
                    )
                }
            }

            }

        }

}


data class shipsUiState(
    val ships: List<ShipsResponseItem> = emptyList(),
    val isLoading: Boolean = true
)