package com.example.footballproject.data.network

import com.example.footballproject.data.models.leagues.LeaguesResponse
import com.example.footballproject.data.models.matches.MatchesTodayResponse
import com.example.footballproject.data.models.table.LeagueTableResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FootballService {

    @Headers("X-Auth-Token: 08a39b7bb9d04a35986e53bd6fd60a65")
    @GET("/v4/matches")
    suspend fun getMatches(): MatchesTodayResponse

    @Headers("X-Auth-Token: 08a39b7bb9d04a35986e53bd6fd60a65")
    @GET("/v4/competitions")
    suspend fun getLeagues(): LeaguesResponse

    @Headers("X-Auth-Token: 08a39b7bb9d04a35986e53bd6fd60a65")
    @GET("/v4/competitions/{code}/standings")
    suspend fun getLeagueTable(@Path("code") code: String): LeagueTableResponse
}