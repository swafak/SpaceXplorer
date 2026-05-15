package com.example.data.room

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
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

//    fun isFavoriteDragon(id: String): Flow<Boolean> = flow {
//        emit( Dao.isFavoriteDragon(id) != null)
//    }
fun isFavoriteDragon(id: String): Flow<Boolean> =
    Dao.getAllDragon().map { dragons ->
        dragons.any { it.id == id }
    }
    fun getFavoriteDragon(): Flow<List<DragonEntity>> {
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

    fun isFavoriteRocket(id: String):Flow<Boolean> = flow {
        emit(Dao.isFavoriteRocket(id) != null)
    }.flowOn(Dispatchers.IO)
        .catch {
            e ->

        }

    fun getFavoriteRocket(): Flow<List<RocketEntity>>{
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
   fun isFavoriteShip(id: String):Flow<Boolean> = flow {
            emit(Dao.isFavoriteShip(id) !=null)

    }
    fun getFavoriteShip(): Flow<List<ShipsEntity>>{
        return Dao.getAllShips()
    }
}