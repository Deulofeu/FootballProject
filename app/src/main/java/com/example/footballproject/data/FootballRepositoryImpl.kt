package com.example.footballproject.data

import android.util.Log
import com.example.footballproject.Result
import com.example.footballproject.data.database.LeaguesDatabase
import com.example.footballproject.data.mappers.leagues.LeaguesMapper
import com.example.footballproject.data.mappers.table.LeagueTableMapper
import com.example.footballproject.data.network.FootballService
import com.example.footballproject.domain.leagues.Leagues
import com.example.footballproject.domain.FootballRepository
import com.example.footballproject.domain.table.LeagueTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FootballRepositoryImpl @Inject constructor(
    private val service: FootballService,
    private val leaguesMapper: LeaguesMapper,
    private val leagueTableEntityToLeagueTableMapper: LeagueTableMapper,
    database: LeaguesDatabase
) : FootballRepository {
    private val dao = database.getLeaguesDatabaseDao()

    override suspend fun getLeagues(): Result<Leagues?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getLeagues()
                val data = response.competitions?.map {
                    leaguesMapper(it)
                }?.let {
                    Leagues(
                        it.toList()
                    )
                }
                if (data?.competitions?.isNotEmpty() == true) {
                    withContext(Dispatchers.IO) {
                        leaguesMapper.leaguesDatabaseMapper(data.competitions)?.let {
                            dao.deleteAllFromTable()
                            dao.insertAll(it)
                        }
                    }
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