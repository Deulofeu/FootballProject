package com.example.footballproject.data.mappers.matches


import com.example.footballproject.data.models.matches.MatchesResponse
import com.example.footballproject.domain.matches.*
import javax.inject.Inject

class MatchesMapper @Inject constructor() {
    operator fun invoke(matchEntity: MatchesResponse): Match =
        with(matchEntity) {
            return Match(
                id ?: 0,
                Competition(
                    competition?.name.orEmpty()
                ),
                Team(
                    homeTeam?.name.orEmpty(),
                    homeTeam?.crest.orEmpty()
                ),
                Team(
                    awayTeam?.name.orEmpty(),
                    awayTeam?.crest.orEmpty()
                ),
                Score(
                    Time(
                        score?.halfTime?.home ?: 0,
                        score?.halfTime?.away ?: 0
                    ),
                    Time(
                        score?.fullTime?.home ?: 0,
                        score?.fullTime?.away ?: 0
                    ),
                    score?.winner
                ),
                status.orEmpty(),
                utcDate.orEmpty()
            )
        }
}