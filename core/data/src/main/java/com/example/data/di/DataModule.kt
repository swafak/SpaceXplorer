package com.example.data.di
import android.content.Context
import androidx.room.Room
import com.example.data.room.DatabasePassphrase
import com.example.data.room.FavoriteDB
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


object DataModule {
    val module = module {
//
//            single {
//                Room.databaseBuilder(
//                    androidContext(),
//                    FavoriteDB::class.java,
//                    "SpaceX-DBases"
//                ).build()
//            }
//        single {
////            System.loadLibrary(passphrase)
//            val passphrase = "122334thisEncryptiom"
//            val passphraseBytes = SQLiteDatabase.getBytes(passphrase.toCharArray())
//            val factory = SupportFactory(passphraseBytes)
//
//            Room.databaseBuilder(
////                context.applicationContext,
//                androidContext(),
//                    FavoriteDB::class.java,
//                    "SpaceX-DBasesss"
//            ).openHelperFactory(factory)
//                .fallbackToDestructiveMigration()
//                .build()
//        }


        single { DatabasePassphrase(get()) }

        single { SupportFactory(get<DatabasePassphrase>().getPassphrase()) }

        single {
            Room.databaseBuilder(get(),FavoriteDB::class.java, "SpaceX-DBasesss.db")
                .fallbackToDestructiveMigration()
                .openHelperFactory(get<SupportFactory>())
                .build()
        }

            single { get<FavoriteDB>().Dao() }

    }
}