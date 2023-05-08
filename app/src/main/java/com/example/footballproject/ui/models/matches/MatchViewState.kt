package com.example.footballproject.ui.models.matches

import com.example.footballproject.domain.matches.Competition
import com.example.footballproject.domain.matches.Score
import com.example.footballproject.domain.matches.Team

data class MatchViewState(
    val id: Int,
    val competition: Competition,
    val homeTeam: Team,
    val awayTeam: Team,
    val score: Score,
    val status: String,
    val utcDate: String
)
