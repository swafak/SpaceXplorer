package com.example.features.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.data.room.DbRepository
import com.example.data.room.DragonEntity
import com.example.data.room.RocketEntity
import com.example.data.room.ShipsEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FavoritesViewModel(private val repository: DbRepository) : ViewModel() {

    val favoriteRocket: LiveData<List<RocketEntity>> = liveData {
        emitSource(repository.getFavoriteRocket())
    }
    val favoriteShip: LiveData<List<ShipsEntity>> = liveData {
        emitSource(repository.getFavoriteShip())
    }
    val favDragon: LiveData<List<DragonEntity>> = liveData {
        emitSource(repository.getFavoriteDragon())
    }
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

//    fun isFavDragon(id: String) = liveData {
//        emit(repository.isFavoriteDragon(id))
//
//    }
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

    fun GetAllDragon(favDragon: DragonEntity){
        viewModelScope.launch {
            repository.getFavoriteDragon()
        }
    }
//    fun getAllRocket(favRocketEntity: RocketEntity){
//        viewModelScope.launch {
//            repository.getFavoriteRocket()
//        }
//    }



}