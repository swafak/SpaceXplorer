package com.example.features.ships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.data.ShipsResponseItem
import com.example.network.model.repository.ShipsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShipsViewModel(private val repository: ShipsRepository) : ViewModel() {

    private val _shipsData = MutableStateFlow<List<ShipsResponseItem>>(emptyList())

    val ships = _shipsData.asStateFlow()

    fun fetchShips(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){
                repository.getShipsInfo()}
                _shipsData.update {
                    response
                }
            }

        }

}