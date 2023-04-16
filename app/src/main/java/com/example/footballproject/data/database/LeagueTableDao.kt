package com.example.footballproject.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LeagueTableDao {

    @Query("SELECT * FROM league_table")
    fun getAllTable(): LiveData<List<LeagueTableEntity>>

    @Insert
    fun insertAll(seasonItemDatabase: List<LeagueTableEntity>)

    @Query("DELETE FROM league_table")
    fun deleteAllFromTable()
}