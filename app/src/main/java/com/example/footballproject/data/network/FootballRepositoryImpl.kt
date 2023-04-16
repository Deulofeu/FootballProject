package com.example.footballproject.data.network

import android.util.Log
import com.example.footballproject.Result
import com.example.footballproject.data.mappers.leagues.LeaguesMapper
import com.example.footballproject.data.mappers.table.LeagueTableMapper
import com.example.footballproject.domain.leagues.Leagues
import com.example.footballproject.domain.FootballRepository
import com.example.footballproject.domain.leagues.Competition
import com.example.footballproject.domain.table.LeagueTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FootballRepositoryImpl @Inject constructor(
    private val service: FootballService,
    private val competitionXEntityToCompetitionXMapper: LeaguesMapper,
    private val leagueTableEntityToLeagueTableMapper: LeagueTableMapper
) : FootballRepository {

    override suspend fun getLeagues(): Result<Leagues> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getLeagues()
                val data = Leagues(
                    response.competitions.map {
                        competitionXEntityToCompetitionXMapper(it)
                    }.toMutableList()
                )
                if (data.competitions.isNotEmpty()) {

                    Result.Success(data)
                } else {
                    Result.Error(IllegalStateException("Empty list"))
                }
            } catch (exception: Exception) {
                Log.e("Error", exception.message, exception)
                Result.Error(exception)
            }
        }
    }

    override suspend fun getLeagueTable(code: String): Result<LeagueTable> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getLeagueTable(code)
                val data = leagueTableEntityToLeagueTableMapper.invoke(response)
                if (data.standings.isNotEmpty()) {
                    Result.Success(data)
                } else {
                    Result.Error(IllegalStateException("Empty list"))
                }
            } catch (exception: Exception) {
                Log.e("Error", exception.message, exception)
                Result.Error(exception)
            }
        }
    }
}