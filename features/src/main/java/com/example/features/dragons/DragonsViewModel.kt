package com.example.features.dragons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.data.DragonResponse
import com.example.network.model.repository.DragonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DragonsViewModel (private val repository: DragonRepository): ViewModel() {

        private val _dragonInfo = MutableStateFlow<List<DragonResponse>>(emptyList())
        val dragon: StateFlow<List<DragonResponse>> = _dragonInfo.asStateFlow()



        fun fetchLaunches() {
                viewModelScope.launch() {
                        val response = withContext(Dispatchers.IO) {repository.getDragonInfo()}
                       _dragonInfo.update {
                                response
                        }
                }
        }


}