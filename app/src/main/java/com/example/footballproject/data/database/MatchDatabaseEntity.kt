package com.example.footballproject.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.footballproject.domain.matches.Competition
import com.example.footballproject.domain.matches.Score
import com.example.footballproject.domain.matches.Team

@Entity(tableName = "db_matches")
data class MatchDatabaseEntity(
    @PrimaryKey
    @ColumnInfo(name = "dbId") val dbId: Int,
    @ColumnInfo(name = "dbCompetition") val dbCompetition: Competition,
    @ColumnInfo(name = "dbHomeTeam") val dbHomeTeam: Team,
    @ColumnInfo(name = "dbAwayTeam") val dbAwayTeam: Team,
    @ColumnInfo(name = "dbScore") val dbScore: Score,
    @ColumnInfo(name = "dbStatus") val dbStatus: String,
    @ColumnInfo(name = "dbUtcDate") val dbUtcDate: String
)
