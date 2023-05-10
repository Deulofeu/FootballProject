package com.example.footballproject.data.mappers.table

import com.example.footballproject.data.database.LeagueTableDatabaseEntity
import com.example.footballproject.data.models.table.LeagueTableResponse
import com.example.footballproject.domain.table.LeagueTable
import com.example.footballproject.domain.table.Standing
import com.example.footballproject.domain.table.Table
import com.example.footballproject.domain.table.Team
import javax.inject.Inject

class LeagueTableMapper @Inject constructor() {

    operator fun invoke(leagueTableResponse: LeagueTableResponse): LeagueTable {
        return LeagueTable(
            leagueTableResponse.competition?.id ?: 0,
            leagueTableResponse.competition?.code.orEmpty(),
            leagueTableResponse.standings?.map { standingResponse ->
                Standing(
                    standingResponse.stage.orEmpty(),
                    standingResponse.table?.map { tableEntity ->
                        Table(
                            tableEntity.position ?: 0,
                            Team(
                                tableEntity.team?.crest.orEmpty(),
                                tableEntity.team?.name.orEmpty()
                            ),
                            tableEntity.playedGames ?: 0,
                            tableEntity.won ?: 0,
                            tableEntity.draw ?: 0,
                            tableEntity.lost ?: 0,
                            tableEntity.goalDifference ?: 0,
                            tableEntity.points ?: 0
                        )
                    } ?: listOf()
                )
            } ?: listOf()
        )
    }

    fun leagueTableDatabaseMapper(leagueTable: LeagueTable): LeagueTableDatabaseEntity {
        return LeagueTableDatabaseEntity(
            leagueTable.id,
            leagueTable.code,
            leagueTable.standings.map { standing ->
                Standing(
                    standing.stage,
                    standing.table.map { table ->
                        Table(
                            table.position,
                            Team(
                                table.team.crest,
                                table.team.name
                            ),
                            table.playedGames,
                            table.won,
                            table.draw,
                            table.lost,
                            table.goalDifference,
                            table.points
                        )
                    }
                )
            }
        )
    }

    fun leagueTableDatabaseToDataMapper(leagueTableDatabaseEntity: LeagueTableDatabaseEntity): LeagueTable {
        return LeagueTable(
            leagueTableDatabaseEntity.dbId,
            leagueTableDatabaseEntity.dbCode,
            leagueTableDatabaseEntity.dbStandings.map { standing ->
                Standing(
                    standing.stage,
                    standing.table.map { table ->
                        Table(
                            table.position,
                            Team(
                                EMPTY_STRING,
                                table.team.name
                            ),
                            table.playedGames,
                            table.won,
                            table.draw,
                            table.lost,
                            table.goalDifference,
                            table.points
                        )
                    }
                )
            }
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}