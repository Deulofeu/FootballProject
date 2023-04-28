package com.example.footballproject.data.mappers.table

import com.example.footballproject.data.model.table.LeagueTableResponse
import com.example.footballproject.domain.table.*
import javax.inject.Inject

class LeagueTableMapper @Inject constructor() {

    operator fun invoke(leagueTableEntity: LeagueTableResponse): LeagueTable {
        return LeagueTable(
            leagueTableEntity.standings?.map { standingResponse ->
                Standing(
                    standingResponse.stage ?: "",
                    standingResponse.table?.map { tableEntity ->
                        Table(
                            tableEntity.position ?: 0,
                            Team(
                                tableEntity.team?.crest ?: "",
                                tableEntity.team?.name ?: ""
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

    fun tableToTableViewStateMapper(table: Table): TableView {
        return TableView(
            table.position,
            table.team,
            table.playedGames,
            table.won,
            table.draw,
            table.lost,
            table.goalDifference,
            table.points
        )
    }
}