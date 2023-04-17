package com.example.footballproject.di

import android.content.Context
import androidx.room.Room
import com.example.footballproject.data.database.LeaguesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun createDatabase(@ApplicationContext context: Context): LeaguesDatabase {
        return Room.databaseBuilder(
            context,
            LeaguesDatabase::class.java,
            "db_leagues"
        ).build()
    }
}