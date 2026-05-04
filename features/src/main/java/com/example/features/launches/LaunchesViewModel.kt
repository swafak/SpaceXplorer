package com.example.features.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.model.Launch
import com.example.features.model.toModel
import com.example.network.model.repository.LaunchesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LaunchesViewModel(
    private val repository: LaunchesRepository
) : ViewModel() {
    private val _launches = MutableStateFlow<List<Launch>>(emptyList())
    val launches: StateFlow<List<Launch>> = _launches

    fun getLaunches() {
        viewModelScope.launch {
            val result = repository.getLaunchesInfo()
            _launches.value = result.map { it.toModel() }
        }
    }
}