package com.example.footballproject.data.mappers.leagues

import com.example.footballproject.data.model.leagues.CompetitionResponse
import com.example.footballproject.domain.leagues.Competition
import com.example.footballproject.domain.leagues.CompetitionView
import javax.inject.Inject

class LeaguesMapper @Inject constructor() {
    operator fun invoke(competitionXEntity: CompetitionResponse): Competition =
        with(competitionXEntity) {
            return Competition(
                code = code.orEmpty(),
                emblem = emblem.orEmpty(),
                id = id ?: 0,
                name = name.orEmpty(),
                type = type.orEmpty()
            )
        }

    fun competitionToCompetitionViewStateMapper(competitionX: Competition): CompetitionView {
        return CompetitionView(
            competitionX.code,
            competitionX.emblem,
            competitionX.id,
            competitionX.name,
            competitionX.type
        )
    }
}