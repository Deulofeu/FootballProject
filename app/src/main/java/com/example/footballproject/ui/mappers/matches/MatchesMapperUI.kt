package com.example.footballproject.ui.mappers.matches

import com.example.footballproject.utils.DateConverter
import com.example.footballproject.domain.matches.Match
import com.example.footballproject.ui.models.matches.MatchViewState
import javax.inject.Inject

class MatchesMapperUI @Inject constructor(private val dateConverter: DateConverter) {

    fun matchToMatchViewMapper(match: Match): MatchViewState {
        return MatchViewState(
            match.id,
            match.competition,
            match.homeTeam,
            match.awayTeam,
            match.score,
            match.status,
            dateConverter.getDate(match.utcDate)
        )
    }
}