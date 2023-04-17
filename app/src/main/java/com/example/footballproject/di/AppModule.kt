package com.example.footballproject.di

import android.content.Context
import androidx.room.Room
import com.example.footballproject.data.FootballRepositoryImpl
import com.example.footballproject.data.network.FootballService
import com.example.footballproject.domain.FootballRepository
import com.example.footballproject.CoilImageLoader
import com.example.footballproject.ImageLoader
import com.example.footballproject.data.database.LeaguesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesFootballRepository(footballRepositoryImpl: FootballRepositoryImpl): FootballRepository =
        footballRepositoryImpl


    @Provides
    @Singleton
    fun providesImageLoader(): ImageLoader {
        return CoilImageLoader
    }

    @Provides
    fun createDatabase(@ApplicationContext context: Context): LeaguesDatabase {
        return Room.databaseBuilder(
            context,
            LeaguesDatabase::class.java,
            "db_leagues"
        ).build()
    }

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}