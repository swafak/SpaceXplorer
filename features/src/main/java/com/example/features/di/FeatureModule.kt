package com.example.features.di

import com.example.features.company.CompanyViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object FeatureModule {
    val module = module {
        viewModel { CompanyViewModel() }
        single {  }
    }
}