package com.example.features.favorites.rocket

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.room.DbRepository
import com.example.data.room.RocketEntity
import com.example.network.model.data.RocketsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteRocketViewModel(private val repository: DbRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<favoriteUiState> = MutableStateFlow(favoriteUiState())
    val uiState = _uiState.asStateFlow()


    private fun insertRocket(favRocketEntity: RocketEntity) {
        viewModelScope.launch {
            repository.insertRocket(favRocketEntity)
        }
    }

    private fun deleteRocket(id: String) {
        viewModelScope.launch {
            repository.deleteRocketById(id)
        }
    }

    fun isFavRocket(id: String) {
        viewModelScope.launch {
            repository.isFavoriteRocket(id).collect { response ->
                _uiState.update {
                    it.copy(
                        isFavoriteState = response
                    )
                }
            }
        }
    }

    private fun addToFav(item: RocketsResponse) {
        val rocketEntity = RocketEntity(
            id = item.id,
            name = item.name,
            type = item.type,
            country = item.country,
            costPerLaunch = item.costPerLaunch,
            description = item.description,
            wikipedia = item.wikipedia,
            stages = item.stages,
            successRatePct = item.successRatePct,
            firstFlight = item.firstFlight,
            flickrImages = item.flickrImages,
            active = item.active,
            boosters = item.boosters,
            company = item.company
        )
        insertRocket(rocketEntity)
        _uiState.update {
            it.copy(isFavoriteState = true)
        }
    }

        private fun removeFromFavorite(item: RocketsResponse) {
            item.id.let {
                deleteRocket(it)
                _uiState.update {
                    it.copy(isFavoriteState = false)

                }
            }
        }

        fun getFavRocket() {
            viewModelScope.launch {
                repository.getFavoriteRocket().collectLatest { response ->
                    _uiState.update {
                        it.copy(
                            favoriteRocket = response
                        )
                    }
                }
            }

        }

        fun toggleFavoriteRocket(item: RocketsResponse) {
            val isFav = _uiState.value.isFavoriteState
            if (isFav) {
                removeFromFavorite(item)
            } else {
                addToFav(item)
            }

        }

}
data class favoriteUiState(
    val favoriteRocket: List<RocketEntity> = emptyList(),
    val isLoading :  Boolean = false,
    val isFavoriteState: Boolean = false
)
