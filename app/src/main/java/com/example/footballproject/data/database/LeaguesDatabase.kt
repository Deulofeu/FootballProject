package com.example.footballproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LeaguesDatabaseEntity::class], version = 1)
abstract class LeaguesDatabase : RoomDatabase() {
    abstract fun getLeaguesDatabaseDao(): LeaguesDatabaseDao
}