package com.example.footballproject.ui.mappers.table

import com.example.footballproject.domain.table.Table
import com.example.footballproject.ui.models.table.TableView
import javax.inject.Inject

class LeagueTableMapperUI @Inject constructor() {

    fun tableToTableViewMapper(table: Table): TableView {
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