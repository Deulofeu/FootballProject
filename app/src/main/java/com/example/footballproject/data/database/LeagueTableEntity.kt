package com.example.footballproject.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "league_table")
data class LeagueTableEntity(
    @PrimaryKey
    @ColumnInfo(name = "position") val position: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "playedGames") val playedGames: Int?,
    @ColumnInfo(name = "won") val won: Int?,
    @ColumnInfo(name = "draw") val draw: Int?,
    @ColumnInfo(name = "lost") val lost: Int?,
    @ColumnInfo(name = "goalsFor") val goalsFor: Int?,
    @ColumnInfo(name = "goalsAgainst") val goalsAgainst: Int?,
    @ColumnInfo(name = "goalDifference") val goalDifference: Int?,
    @ColumnInfo(name = "points") val points: Int?,
)