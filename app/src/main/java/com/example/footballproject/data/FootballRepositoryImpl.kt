package com.example.footballproject.data

import com.example.footballproject.Result
import com.example.footballproject.data.database.LeaguesDatabase
import com.example.footballproject.data.mappers.leagues.LeaguesMapper
import com.example.footballproject.data.mappers.matches.MatchesMapper
import com.example.footballproject.data.mappers.table.LeagueTableMapper
import com.example.footballproject.data.network.FootballService
import com.example.footballproject.domain.leagues.Leagues
import com.example.footballproject.domain.FootballRepository
import com.example.footballproject.domain.matches.MatchesToday
import com.example.footballproject.domain.table.LeagueTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FootballRepositoryImpl @Inject constructor(
    private val service: FootballService,
    private val leaguesMapper: LeaguesMapper,
    private val leagueTableMapper: LeagueTableMapper,
    private val matchesMapper: MatchesMapper,
    database: LeaguesDatabase
) : FootballRepository {
    private val dao = database.getLeaguesDatabaseDao()

    override suspend fun getMatches(): Result<MatchesToday> {
        return withContext(Dispatchers.IO) {
            val response = service.getMatches()
            val data = MatchesToday(
                response.matches?.map {
                    matchesMapper.invoke(it)
                }?.toList() ?: listOf()
            )
            if (data.matches.isNotEmpty()) {
                Result.Success(data)
            } else {
                Result.Error(IllegalArgumentException("Empty matches today list"))
            }
        }
    }

    override suspend fun getLeagues(): Result<Leagues> {
        return withContext(Dispatchers.IO) {
            val response = service.getLeagues()
            val data = Leagues(
                response.competitions?.map {
                    leaguesMapper(it)
                }?.toList() ?: listOf()
            )
            if (data.competitions.isNotEmpty()) {
                withContext(Dispatchers.IO) {
                    leaguesMapper.leaguesDatabaseMapper(data.competitions).let {
                        dao.deleteAllFromTable()
                        dao.insertAll(it)
                    }
                }
                Result.Success(data)
            } else {
                Result.Error(IllegalArgumentException("Empty leagues list"))
            }
        }
    }

    override suspend fun getLeagueTable(code: String): Result<LeagueTable> {
        return withContext(Dispatchers.IO) {
            val response = service.getLeagueTable(code)
            val data = leagueTableMapper.invoke(response)
            if (data.standings.isNotEmpty()) {
                Result.Success(data)
            } else {
                Result.Error(IllegalArgumentException("Empty league table"))
            }
        }
    }
}