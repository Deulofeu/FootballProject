package com.example.footballproject.data.mappers.matches


import com.example.footballproject.data.model.matches.MatchesResponse
import com.example.footballproject.domain.matches.*
import javax.inject.Inject

class MatchesMapper @Inject constructor() {
    operator fun invoke(matchEntity: MatchesResponse): Match =
        with(matchEntity) {
            return Match(
                id ?: 0,
                Competition(
                    competition?.code ?: "",
                    competition?.emblem ?: "",
                    competition?.id ?: 0,
                    competition?.name ?: "",
                    competition?.type ?: ""
                ),
                HomeTeam(
                    homeTeam?.id ?: 0,
                    homeTeam?.name ?: "",
                    homeTeam?.shortName ?: "",
                    homeTeam?.tla ?: "",
                    homeTeam?.crest ?: "",
                ),
                AwayTeam(
                    awayTeam?.id ?: 0,
                    awayTeam?.name ?: "",
                    awayTeam?.shortName ?: "",
                    awayTeam?.tla ?: "",
                    awayTeam?.crest ?: "",
                ),
                Score(
                    HalfTime(
                        score?.halfTime?.home ?: 0,
                        score?.halfTime?.away ?: 0
                    ),
                    FullTime(
                        score?.fullTime?.home ?: 0,
                        score?.fullTime?.away ?: 0
                    ),
                    score?.winner
                ),
                status ?: "",
                utcDate ?: ""
            )
        }

    fun matchToMatchView(match: Match): MatchViewState {
        return MatchViewState(
            match.id,
            match.competition,
            match.homeTeam,
            match.awayTeam,
            match.score,
            match.status,
            match.utcDate
        )
    }
}