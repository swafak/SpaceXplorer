package com.example.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDragon(favoriteDragon: DragonEntity )

    @Query("SELECT * FROM FavDragon")
    fun getAllDragon(): Flow<List<DragonEntity>>

    @Query("DELETE FROM FavDragon WHERE id = :id")
    suspend fun deleteDragonById(id: String)

    @Query("SELECT * FROM FavDragon WHERE id = :id LIMIT 1")
    suspend fun isFavoriteDragon(id: String): DragonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRocket(favRocket: RocketEntity )

    @Query("SELECT * FROM FavRocket")
    fun getAllRocket(): Flow<List<RocketEntity>>

    @Query("DELETE FROM FavRocket WHERE id = :id")
    suspend fun deleteRocketById(id: String)

    @Query("SELECT * FROM FavRocket WHERE id = :id LIMIT 1")
    suspend fun isFavoriteRocket(id: String): RocketEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShips(favoriteShips: ShipsEntity)

    @Query("SELECT * FROM FavShips")
    fun getAllShips(): Flow<List<ShipsEntity>>

    @Query("DELETE FROM FavShips WHERE id = :id")
    suspend fun deleteShips(id: String)

    @Query("SELECT * FROM FavShips WHERE id = :id LIMIT 1")
    suspend fun isFavoriteShip(id: String) : ShipsEntity



}