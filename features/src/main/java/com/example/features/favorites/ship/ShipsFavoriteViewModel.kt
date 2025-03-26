package com.example.features.favorites.ship

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.room.DbRepository
import com.example.data.room.ShipsEntity
import com.example.network.model.data.ShipsResponseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShipsFavoriteViewModel(private val repository: DbRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<favoriteUiState> = MutableStateFlow(favoriteUiState())
    val uiState = _uiState.asStateFlow()



    private fun insertShips(favoriteShip: ShipsEntity) {
        viewModelScope.launch {
            repository.insertShips(favoriteShip)
        }
    }

    private fun deleteShip(id: String) {
        viewModelScope.launch {
            repository.deleteShipById(id)
        }
    }

    fun isFavShip(id: String) {
        viewModelScope.launch {
            repository.isFavoriteShip(id).collect { response ->
                _uiState.update {
                    it.copy(
                        isFavoriteState = response
                    )
                }
            }
        }
    }

    private fun addToFav(item: ShipsResponseItem) {
        val shipsEntity = ShipsEntity(
            id = item.id,
            name = item.name,
            type = item.type,
            active = item.active,
            homePort = item.homePort,
            launches = item.launches,
            latitude = item.latitude,
            longitude = item.longitude,
            link = item.link,
            legacyId = item.legacyId,
            image = item.image,
            mmsi = item.mmsi,
            massKg = item.massKg,
            massLbs = item.massLbs,
            model = item.model,
            imo = item.imo,
            yearBuilt = item.yearBuilt,
            status = item.status,
            roles = item.roles, abs = item.abs

        )
        insertShips(shipsEntity)
        _uiState.update {
            it.copy(isFavoriteState = true)
        }
    }

    private fun removeFromFavorite(item: ShipsResponseItem) {
        item.id.let {
            deleteShip(it)
            _uiState.update {
                it.copy(isFavoriteState = false)

            }
        }
    }

    fun getFavShip() {
        viewModelScope.launch {
            repository.getFavoriteShip().collectLatest { response ->
                _uiState.update {
                    it.copy(
                        favoriteShip = response
                    )
                }
            }
        }

    }

    fun toggleFavoriteShip(item: ShipsResponseItem) {
        val isFav = _uiState.value.isFavoriteState
        if (isFav) {
            removeFromFavorite(item)
        } else {
            addToFav(item)
        }

    }


}
data class favoriteUiState(
    val favoriteShip:  List<ShipsEntity> = emptyList(),
    val isLoading:  Boolean = false,
    val isFavoriteState: Boolean = false
)
