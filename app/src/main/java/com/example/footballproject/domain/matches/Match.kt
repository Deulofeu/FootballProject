package com.example.footballproject.domain.matches

data class Match(
    val id: Int,
    val competition: Competition,
    val homeTeam: HomeTeam,
    val awayTeam: AwayTeam,
    val score: Score,
    val status: String,
    var utcDate: String
)