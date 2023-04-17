package com.example.footballproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LeaguesDatabaseDao {
    @Query("SELECT * FROM db_leagues")
    fun getCashedTable(): List<LeaguesDatabaseEntity>

    @Insert
    fun insertAll(itemDatabase: List<LeaguesDatabaseEntity>)

    @Query("DELETE FROM db_leagues")
    fun deleteAllFromTable()
}