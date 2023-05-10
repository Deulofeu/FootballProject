package com.example.footballproject.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.footballproject.domain.table.Standing

@Entity(tableName = "db_league_table")
data class LeagueTableDatabaseEntity(
    @PrimaryKey
    @ColumnInfo(name = "dbId") val dbId: Int,
    @ColumnInfo(name = "dbCode") val dbCode: String,
    @ColumnInfo(name = "dbStandings") val dbStandings: List<Standing>
)
