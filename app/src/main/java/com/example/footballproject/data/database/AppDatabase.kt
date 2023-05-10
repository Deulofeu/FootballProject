package com.example.footballproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.footballproject.data.database.converters.LeagueTableConverters
import com.example.footballproject.data.database.converters.MatchConverters

@Database(
    entities = [LeaguesDatabaseEntity::class, MatchDatabaseEntity::class, LeagueTableDatabaseEntity::class],
    version = 1
)
@TypeConverters(MatchConverters::class, LeagueTableConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMatchesDatabaseDao(): MatchesDatabaseDao

    abstract fun getLeaguesDatabaseDao(): LeaguesDatabaseDao

    abstract fun getLeagueTableDatabaseDao(): LeagueTableDatabaseDao
}