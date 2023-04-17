package com.example.footballproject.di

import com.example.footballproject.CoilImageLoader
import com.example.footballproject.ImageLoader
import com.example.footballproject.data.FootballRepositoryImpl
import com.example.footballproject.domain.FootballRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun getFootballRepository(footballRepositoryImpl: FootballRepositoryImpl): FootballRepository

    @Binds
    abstract fun getImageLoader(coilImageLoader: CoilImageLoader): ImageLoader
}