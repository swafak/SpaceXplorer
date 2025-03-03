package com.example.features.di

import com.example.features.company.CompanyViewModel
import com.example.features.explore.ExploreViewModel
import com.example.network.model.repository.CompanyRepository
import com.example.network.model.repository.HistoryRepository
import com.example.network.model.repository.LaunchesRepository
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


    }

}