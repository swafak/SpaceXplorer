package com.example.features.dragons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.model.Dragon
import com.example.features.model.Rocket
import com.example.features.model.toModel
import com.example.network.model.data.DragonResponse
import com.example.network.model.repository.DragonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.map

class DragonsViewModel(private val repository: DragonRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(DragonUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchDragon() {
        viewModelScope.launch {
            val response =  repository.getDragonInfo()
            if(response.isEmpty()){
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }else{
            _uiState.update {
                it.copy(

                    dragonInfo = response,
                    isLoading = false,
                    dragon = response.map { it.toModel() },
                )
            }}
        }
    }
}

data class DragonUiState(
    val dragonInfo: List<DragonResponse>? = emptyList(),
    val isLoading: Boolean = true,
    val dragon: List<Dragon> = emptyList()
)