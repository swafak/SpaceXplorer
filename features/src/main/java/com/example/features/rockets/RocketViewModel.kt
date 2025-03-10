package com.example.features.rockets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.data.RocketsResponse
import com.example.network.model.repository.RocketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RocketViewModel(private val repository: RocketRepository) : ViewModel() {

    private val _rocketData = MutableStateFlow<List<RocketsResponse>>(emptyList())

    val rocket : StateFlow<List<RocketsResponse>> = _rocketData.asStateFlow()

    fun fetchRockets(){
        viewModelScope.launch {
            repository.getRocketsInfo().collectLatest { response ->
                _rocketData.value = response
            }
        }
    }
}