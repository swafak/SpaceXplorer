package com.example.data.di
import androidx.room.Room
import com.example.data.room.FavoriteDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


object DataModule {
    val module = module {

            single {
                Room.databaseBuilder(
                    androidContext(),
                    FavoriteDB::class.java,
                    "SpaceX-database"
                ).build()
            }

            single { get<FavoriteDB>().Dao() }

    }
}