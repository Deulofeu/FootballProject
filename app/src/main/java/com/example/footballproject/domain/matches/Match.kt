package com.example.footballproject.domain.matches

data class Match(
    val id: Int,
    val competition: Competition,
    val homeTeam: Team,
    val awayTeam: Team,
    val score: Score,
    val status: String,
    val utcDate: String
)