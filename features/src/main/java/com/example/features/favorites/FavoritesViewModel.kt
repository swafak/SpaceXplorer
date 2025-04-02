package com.example.features.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.data.room.DbRepository
import com.example.data.room.DragonEntity
import com.example.data.room.RocketEntity
import com.example.data.room.ShipsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: DbRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<favoriteUiState> = MutableStateFlow(favoriteUiState())
    val uiState = _uiState.asStateFlow()

    val favoriteShip: Flow<List<ShipsEntity>> = repository.getFavoriteShip()
    val favDragon: Flow<List<DragonEntity>> = repository.getFavoriteDragon()

    fun insertDragon(favDragon: DragonEntity) {
        viewModelScope.launch {
            repository.insertDragon(favDragon)
        }
    }

    fun insertShips(favShip: ShipsEntity) {
        viewModelScope.launch {
            repository.insertShips(favShip)
        }
    }

    fun deleteShip(id: String) {
        viewModelScope.launch {
            repository.deleteShipById(id)
        }
    }

    //
    fun isFavoriteShip(id: String) = liveData {
        emit(repository.isFavoriteShip(id))

    }


    fun getFavoriteShip() {
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

    fun getFavoriteDragon() {
        viewModelScope.launch {
            repository.getFavoriteDragon().collectLatest { response ->
                _uiState.update {
                    it.copy(
                        favDragon = response
                    )
                }
            }
        }
    }

//    fun getFavorite() {
//        viewModelScope.launch {
//            val rocket = repository.getFavoriteRocket()
//            val ships = repository.getFavoriteShip()
//            val dragon = repository.getFavoriteDragon()
//            _uiState.update {
//                it.copy(
//                    favoriteRocket = rocket,
//                    favoriteShip = ships,
//                    favDragon = dragon,
//                    isFavoriteState = true
//                )
//            }
//        }


    fun deleteDragon(id: String) {
        viewModelScope.launch {
            repository.deleteDragonById(id)
        }
    }

    fun deleteRocket(id: String) {
        viewModelScope.launch {
            repository.deleteRocketById(id)
        }
    }
}

data class favoriteUiState(
    val favoriteRocket: List<RocketEntity> = emptyList(),
    val favoriteShip: List<ShipsEntity> = emptyList(),
    val favDragon: List<DragonEntity> = emptyList(),
    val isLoading: Boolean = false,
    val isFavoriteState: Boolean = false
)
