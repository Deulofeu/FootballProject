package com.example.footballproject.data.mappers.table

import com.example.footballproject.data.model.table.LeagueTableResponse
import com.example.footballproject.domain.table.*
import javax.inject.Inject

class LeagueTableMapper @Inject constructor() {

    operator fun invoke(leagueTableEntity: LeagueTableResponse): LeagueTable {
        return LeagueTable(
            leagueTableEntity.standings.map {
                Standing(
                    it.stage,
                    it.table.map { tableEntity ->
                        Table(
                            tableEntity.position,
                            Team(
                                tableEntity.team.crest,
                                tableEntity.team.name
                            ),
                            tableEntity.playedGames,
                            tableEntity.won,
                            tableEntity.draw,
                            tableEntity.lost,
                            tableEntity.goalDifference,
                            tableEntity.points
                        )
                    }
                )
            }
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