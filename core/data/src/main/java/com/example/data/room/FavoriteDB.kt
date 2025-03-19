package com.example.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [RocketEntity::class, DragonEntity::class, ShipsEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FavoriteDB : RoomDatabase(){
    abstract fun Dao(): Dao

    companion object{
        @Volatile private var INSTANCE: FavoriteDB  ? = null

        fun getDatabase(context: Context): FavoriteDB  {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDB ::class.java,
                    "SpaceX-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}