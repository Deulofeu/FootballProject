package com.example.footballproject.domain

import com.example.footballproject.Result
import com.example.footballproject.domain.leagues.Leagues
import com.example.footballproject.domain.matches.MatchesToday
import com.example.footballproject.domain.table.LeagueTable

interface FootballRepository {
    suspend fun getMatches(): Result<MatchesToday>
    suspend fun getLeagues(): Result<Leagues>
    suspend fun getLeagueTable(code: String): Result<LeagueTable>
}