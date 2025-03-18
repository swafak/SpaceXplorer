package com.example.data.room

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DbRepository(private val Dao: Dao) {
    suspend fun insertDragon(favDragon: DragonEntity){
        withContext(Dispatchers.IO){
            Dao.insertDragon(favDragon)
        }
    }

    suspend fun deleteDragonById(id: String){
        Dao.deleteDragonById(id)
    }

    suspend fun isFavoriteDragon(id: String): Boolean{
        return Dao.isFavoriteDragon(id) != null
    }

    fun getFavoriteDragon(): LiveData<List<DragonEntity>>{
        return Dao.getAllDragon()
    }
    suspend fun insertRocket(favEntity: RocketEntity){
        withContext(Dispatchers.IO){
            Dao.insertRocket(favEntity)
        }
    }

    suspend fun deleteRocketById(id: String){
        Dao.deleteRocketById(id)
    }

    suspend fun isFavoriteRocket(id: String): Boolean{
        return Dao.isFavoriteRocket(id) != null
    }

    fun getFavoriteRocket(): LiveData<List<RocketEntity>>{
        return Dao.getAllRocket()
    }

    suspend fun insertShips(favEntity: ShipsEntity){
        withContext(Dispatchers.IO){
            Dao.insertShips((favEntity))
        }
    }
    suspend fun deleteShipById(id: String){
        return Dao.deleteShips(id)
    }
    suspend fun isFavoriteShip(id: String): Boolean{
        return Dao.isFavoriteShip(id) !=null
    }
    fun getFavoriteShip(): LiveData<List<ShipsEntity>>{
        return Dao.getAllShips()
    }
}