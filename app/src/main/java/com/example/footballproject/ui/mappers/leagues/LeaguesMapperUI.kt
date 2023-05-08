package com.example.footballproject.ui.mappers.leagues

import com.example.footballproject.domain.leagues.Competition
import com.example.footballproject.ui.models.leagues.CompetitionView
import javax.inject.Inject

class LeaguesMapperUI @Inject constructor() {

    fun competitionToCompetitionViewMapper(competition: Competition): CompetitionView {
        return CompetitionView(
            competition.code,
            competition.emblem,
            competition.id,
            competition.name
        )
    }
}