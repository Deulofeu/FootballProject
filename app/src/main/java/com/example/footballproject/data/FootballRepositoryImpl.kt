package com.example.footballproject.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.footballproject.CheckNetworkConnection
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
    private val checkNetworkConnection: CheckNetworkConnection,
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
                }?.toList().orEmpty()
            )
            Result.Success(data)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun getLeagues(): Result<Leagues> {
        return if (checkNetworkConnection.isInternetAvailable()) {
            withContext(Dispatchers.IO) {
                val response = service.getLeagues()
                val data = Leagues(
                    response.competitions?.map {
                        leaguesMapper(it)
                    }?.toList().orEmpty()
                )
                if (data.competitions.isNotEmpty()) {
                    withContext(Dispatchers.IO) {
                        val dataBaseList =
                            data.competitions.map { leaguesMapper.leaguesDatabaseMapper(it) }
                        dao.insertAll(dataBaseList)
                    }
                    Result.Success(data)
                } else {
                    Result.Error(IllegalArgumentException("Empty leagues list"))
                }
            }
        } else withContext(Dispatchers.IO) {
            val data = Leagues(
                dao.getCashedTable().map { leaguesMapper.leaguesDatabaseToDataMapper(it) })
            Result.Success(data)
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