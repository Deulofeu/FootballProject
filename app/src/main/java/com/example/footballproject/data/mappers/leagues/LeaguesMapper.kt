package com.example.footballproject.data.mappers.leagues

import com.example.footballproject.data.database.LeaguesDatabaseEntity
import com.example.footballproject.data.model.leagues.CompetitionResponse
import com.example.footballproject.domain.leagues.Competition
import com.example.footballproject.domain.leagues.CompetitionView
import javax.inject.Inject

class LeaguesMapper @Inject constructor() {
    operator fun invoke(competitionEntity: CompetitionResponse): Competition =
        with(competitionEntity) {
            return Competition(
                code = code.orEmpty(),
                emblem = emblem.orEmpty(),
                id = id ?: 0,
                name = name.orEmpty(),
                type = type.orEmpty()
            )
        }

    fun competitionToCompetitionViewStateMapper(competition: Competition): CompetitionView {
        return CompetitionView(
            competition.code,
            competition.emblem,
            competition.id,
            competition.name,
            competition.type
        )
    }

    fun leaguesDatabaseMapper(competition: List<Competition>): List<LeaguesDatabaseEntity> {
        return competition.map {
            LeaguesDatabaseEntity(
                it.code,
                it.emblem,
                it.id,
                it.name,
                it.type
            )
        }
    }
}