package com.example.network.di

import com.example.network.model.api.SpaceXplorerAPI
import com.example.network.model.repository.CompanyRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object CoreNetworkModule {


    val module = module {
        single {
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        }

        // Provide OkHttpClient with logging
        single {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }

        // Provide Retrofit with OkHttpClient and Moshi
        single {
            Retrofit.Builder()
                .baseUrl("https://api.spacexdata.com/")
                .addConverterFactory(MoshiConverterFactory.create(get())) // Get Moshi instance
                .client(get()) // Get OkHttpClient instance
                .build()
            //.create(SpaceXplorerAPI::class.java) // Make sure to return the API service instance
        }

        single {
            get<Retrofit>().create(SpaceXplorerAPI::class.java)
        }
        single { CompanyRepository(get()) }
    }
}