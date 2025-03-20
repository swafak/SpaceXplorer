package com.example.features.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.data.room.DbRepository
import com.example.data.room.DragonEntity
import com.example.data.room.RocketEntity
import com.example.data.room.ShipsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FavoritesViewModel(private val repository: DbRepository) : ViewModel() {

    val favoriteRocket: Flow<List<RocketEntity>> = repository.getFavoriteRocket()

    val favoriteShip: Flow<List<ShipsEntity>> = repository.getFavoriteShip()

    val favDragon: Flow<List<DragonEntity>> = repository.getFavoriteDragon()

    fun insertDragon(favDragon : DragonEntity){
        viewModelScope.launch {
            repository.insertDragon(favDragon)
        }
    }
    fun insertShips(favShip: ShipsEntity){
        viewModelScope.launch {
            repository.insertShips(favShip)
        }
    }
    fun deleteShip(id: String){
        viewModelScope.launch {
            repository.deleteShipById(id)
        }
    }

    fun isFavoriteShip(id: String)= liveData {
        emit(repository.isFavoriteShip(id))

    }

    fun insertRocket(favRocketEntity: RocketEntity){
        viewModelScope.launch {
            repository.insertRocket(favRocketEntity)
        }
    }
    fun isFavDragon(id: String): Boolean {
        return runBlocking { repository.isFavoriteDragon(id) } // Fetch result in a blocking manner
    }
    fun isFavRocket(id: String)= liveData {
        emit(repository.isFavoriteRocket(id))
    }

    fun deleteDragon(id: String){
        viewModelScope.launch {
            repository.deleteDragonById(id)
        }
    }
    fun deleteRocket(id: String){
        viewModelScope.launch {
            repository.deleteRocketById(id)
        }
    }
}