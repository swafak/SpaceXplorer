package com.example.features.favorites.dragon

import com.example.data.room.DragonEntity
import com.example.network.model.data.DragonResponse
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.room.DbRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteDragonViewModel(private val repository: DbRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<DragonUiState> = MutableStateFlow(DragonUiState())
    val uiState = _uiState.asStateFlow()


    private fun insertDragon(favoriteDragon: DragonEntity) {
        viewModelScope.launch {
            repository.insertDragon(favoriteDragon)
        }
    }

    private fun deleteDragon(id: String) {
        viewModelScope.launch {
            repository.deleteDragonById(id)
        }
    }

    fun isFavDragon(id: String) {
        viewModelScope.launch {
            repository.isFavoriteDragon(id).collect { response ->
                _uiState.update {
                    it.copy(
                        isFavoriteState = response
                    )
                }
            }
        }
    }

    private fun addToFav(item: DragonResponse) {
        val entity = DragonEntity(
            id = item.id,
            name = item.name,
            type = item.type,
            active = item.active,
            crewCapacity = item.crewCapacity,
            flickrImages = item.flickrImages,
            description = item.description,
            wikipedia = item.wikipedia,
            dryMassKg = item.dryMassKg,
            firstFlight = item.firstFlight
        )
        insertDragon(entity)
        _uiState.update {
            it.copy(isFavoriteState = true)
        }
    }

    private fun removeFromFavorite(item: DragonResponse) {
        item.id.let {
            deleteDragon(it)
            _uiState.update {
                it.copy(isFavoriteState = false)

            }
        }
    }

    fun getFavDragon() {
        viewModelScope.launch {
            repository.getFavoriteDragon().collectLatest { response ->
                _uiState.update {
                    it.copy(
                        favoriteDragon = response
                    )
                }
            }
        }

    }

    fun toggleFavorite(item: DragonResponse) {
        val isFav = _uiState.value.isFavoriteState
        if (isFav) {
            removeFromFavorite(item)
        } else {
            addToFav(item)
        }
    }


}
data class DragonUiState(
    val favoriteDragon: List<DragonEntity> = emptyList(),
    val isLoading :  Boolean = false,
    val isFavoriteState: Boolean = false
)
