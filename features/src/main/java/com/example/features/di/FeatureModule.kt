package com.example.features.di

import com.example.features.company.CompanyViewModel
import com.example.features.dragons.DragonsViewModel
import com.example.features.explore.ExploreViewModel
import com.example.features.rockets.RocketViewModel
import com.example.features.ships.ShipsViewModel
import com.example.network.model.repository.CompanyRepository
import com.example.network.model.repository.DragonRepository
import com.example.network.model.repository.HistoryRepository
import com.example.network.model.repository.LaunchesRepository
import com.example.network.model.repository.RocketRepository
import com.example.network.model.repository.ShipsRepository
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object FeatureModule {
    val module = module {
        single { CompanyRepository(get()) }
        viewModel { CompanyViewModel(get()) }
        single { LaunchesRepository(get()) }

        single { HistoryRepository(get()) }
        viewModelOf(::ExploreViewModel)

        single { RocketRepository(get()) }
        viewModel { RocketViewModel(get()) }

        single { DragonRepository(get()) }
        viewModel{DragonsViewModel(get())}
        single { ShipsRepository (get())}
        viewModel{ShipsViewModel(get())}



    }

}