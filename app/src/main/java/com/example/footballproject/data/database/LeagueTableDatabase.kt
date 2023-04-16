package com.example.footballproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [LeagueTableEntity::class], version = 1)
abstract class LeagueTableDatabase : RoomDatabase() {
    abstract fun getSeasonTableDao(): LeagueTableDao
}