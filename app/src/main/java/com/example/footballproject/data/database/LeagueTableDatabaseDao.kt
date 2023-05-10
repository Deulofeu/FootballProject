package com.example.footballproject.data.database

import androidx.room.*

@Dao
interface LeagueTableDatabaseDao {

    @Query("SELECT * FROM db_league_table")
    fun getCashedTable(): List<LeagueTableDatabaseEntity>

    @Query("SELECT * FROM db_league_table WHERE dbCode = :code")
    fun getLeagueTable(code: String): LeagueTableDatabaseEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(itemDatabase: LeagueTableDatabaseEntity)

    @Query("DELETE FROM db_league_table")
    fun deleteAllFromTable()
}