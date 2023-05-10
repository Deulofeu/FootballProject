package com.example.footballproject.data

import com.example.footballproject.CheckNetworkConnection
import com.example.footballproject.Result
import com.example.footballproject.data.database.AppDatabase
import com.example.footballproject.data.mappers.leagues.LeaguesMapper
import com.example.footballproject.data.mappers.matches.MatchesMapper
import com.example.footballproject.data.mappers.table.LeagueTableMapper
import com.example.footballproject.data.network.FootballService
import com.example.footballproject.domain.FootballRepository
import com.example.footballproject.domain.leagues.Leagues
import com.example.footballproject.domain.matches.MatchesToday
import com.example.footballproject.domain.table.LeagueTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FootballRepositoryImpl @Inject constructor(
    private val checkNetworkConnection: CheckNetworkConnection,
    private val service: FootballService,
    private val leaguesMapper: LeaguesMapper,
    private val leagueTableMapper: LeagueTableMapper,
    private val matchesMapper: MatchesMapper,
    database: AppDatabase
) : FootballRepository {
    private val daoLeagues = database.getLeaguesDatabaseDao()
    private val daoMatches = database.getMatchesDatabaseDao()

    override suspend fun getMatches(): Result<MatchesToday> {
        return if (checkNetworkConnection.isInternetAvailable()) {
            withContext(Dispatchers.IO) {
                val response = service.getMatches()
                val data = MatchesToday(
                    response.matches?.map {
                        matchesMapper.invoke(it)
                    }?.toList().orEmpty()
                )
                withContext(Dispatchers.IO) {
                    daoMatches.deleteAllFromTable()
                    val dataBaseList =
                        data.matches.map { matchesMapper.matchesDatabaseMapper(it) }
                    daoMatches.insertAll(dataBaseList)
                }
                Result.Success(data)
            }
        } else withContext(Dispatchers.IO) {
            val data = MatchesToday(
                daoMatches.getCashedTable().map { matchesMapper.matchesDatabaseToDataMapper(it) })
            Result.Success(data)
        }
    }

    override suspend fun getLeagues(): Result<Leagues> {
        return if (checkNetworkConnection.isInternetAvailable()) {
            withContext(Dispatchers.IO) {
                val response = service.getLeagues()
                val data = Leagues(
                    response.competitions?.map {
                        leaguesMapper(it)
                    }?.toList().orEmpty()
                )
                withContext(Dispatchers.IO) {
                    daoLeagues.deleteAllFromTable()
                    val dataBaseList =
                        data.competitions.map { leaguesMapper.leaguesDatabaseMapper(it) }
                    daoLeagues.insertAll(dataBaseList)
                }
                Result.Success(data)
            }
        } else withContext(Dispatchers.IO) {
            val data = Leagues(
                daoLeagues.getCashedTable().map { leaguesMapper.leaguesDatabaseToDataMapper(it) })
            Result.Success(data)
        }
    }

    override suspend fun getLeagueTable(code: String): Result<LeagueTable> {
        return if (checkNetworkConnection.isInternetAvailable()) {
            withContext(Dispatchers.IO) {
                val response = service.getLeagueTable(code)
                val data = LeagueTable(
                    response.standings?.map {
                        leagueTableMapper.invoke(it)
                    }?.toList().orEmpty()
                )
                Result.Success(data)
            }
        } else withContext(Dispatchers.IO) {
            Result.Success(LeagueTable(listOf()))
        }
    }
}
