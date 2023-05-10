package com.example.footballproject.data.mappers.table

import com.example.footballproject.data.models.table.StandingResponse
import com.example.footballproject.domain.table.Standing
import com.example.footballproject.domain.table.Table
import com.example.footballproject.domain.table.Team
import javax.inject.Inject

class LeagueTableMapper @Inject constructor() {

    operator fun invoke(standingResponse: StandingResponse): Standing {
        return Standing(
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
    }

}
