package com.example.footballproject.di

import com.example.footballproject.data.network.FootballRepositoryImpl
import com.example.footballproject.data.network.FootballService
import com.example.footballproject.domain.FootballRepository
import com.example.footballproject.CoilImageLoader
import com.example.footballproject.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesFootballServiceService(): FootballService = NetworkModule.getService()


    @Provides
    fun providesFootballRepository(footballRepositoryImpl: FootballRepositoryImpl): FootballRepository = footballRepositoryImpl


    @Provides
    @Singleton
    fun providesImageLoader(): ImageLoader {
        return CoilImageLoader
    }

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}