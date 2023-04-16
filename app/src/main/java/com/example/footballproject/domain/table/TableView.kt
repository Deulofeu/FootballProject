package com.example.footballproject.domain.table

data class TableView(
    val position: Int,
    val team: Team,
    val playedGames: Int,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val goalDifference: Int,
    val points: Int
)