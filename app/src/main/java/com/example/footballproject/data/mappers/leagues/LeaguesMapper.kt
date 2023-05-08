package com.example.footballproject.data.mappers.leagues

import com.example.footballproject.data.database.LeaguesDatabaseEntity
import com.example.footballproject.data.models.leagues.CompetitionResponse
import com.example.footballproject.domain.leagues.Competition
import javax.inject.Inject

class LeaguesMapper @Inject constructor() {
    operator fun invoke(competitionEntity: CompetitionResponse): Competition =
        with(competitionEntity) {
            return Competition(
                code = code.orEmpty(),
                emblem = emblem.orEmpty(),
                id = id ?: 0,
                name = name.orEmpty()
            )
        }

    fun leaguesDatabaseMapper(competition: Competition): LeaguesDatabaseEntity {
        return LeaguesDatabaseEntity(
            competition.code,
            competition.emblem,
            competition.id,
            competition.name
        )
    }

    fun leaguesDatabaseToDataMapper(entity: LeaguesDatabaseEntity): Competition {
        return Competition(
            entity.dbCode,
            EMPTY_STRING,
            entity.dbId,
            entity.dbName
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}