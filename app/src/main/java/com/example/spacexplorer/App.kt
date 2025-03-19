package com.example.spacexplorer

import android.app.Application
import com.example.data.di.DataModule
import com.example.features.di.FeatureModule
import com.example.network.di.CoreNetworkModule
import com.example.network.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
//                AppModule.module,
                FeatureModule.module,
                NetworkModule.module,
                CoreNetworkModule.module,
                DataModule.module
                )
        }
    }

}