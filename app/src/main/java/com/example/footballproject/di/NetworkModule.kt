package com.example.footballproject.di

import com.example.footballproject.data.network.FootballService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkModule {
    companion object {
        fun getService(): FootballService {
            val build = Retrofit.Builder()
                .baseUrl("https://api.football-data.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return build.create(FootballService::class.java)
        }
    }
}