package com.example.footballproject.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "db_leagues")
data class LeaguesDatabaseEntity(
    @PrimaryKey
    @ColumnInfo(name = "dbCode") val dbCode: String,
    @ColumnInfo(name = "dbEmblem") val dbEmblem: String,
    @ColumnInfo(name = "dbId") val dbId: Int,
    @ColumnInfo(name = "dbName") val dbName: String,
    @ColumnInfo(name = "dbType") val dbType: String,
)
