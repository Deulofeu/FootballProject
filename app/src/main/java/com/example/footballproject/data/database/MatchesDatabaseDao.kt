package com.example.footballproject.data.database

import androidx.room.*

@Dao
interface MatchesDatabaseDao {

    @Query("SELECT * FROM db_matches")
    fun getCashedTable(): List<MatchDatabaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(itemDatabase: List<MatchDatabaseEntity>)

    @Query("DELETE FROM db_matches")
    fun deleteAllFromTable()
}