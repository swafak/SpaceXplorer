package com.example.spacexplorer

import android.app.Application
import com.example.spacexplorer.AppModule.appModule
import org.koin.core.context.startKoin

class App :Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }

}