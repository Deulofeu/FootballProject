package com.example.footballproject.data.mappers.matches


import com.example.footballproject.data.database.MatchDatabaseEntity
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

    fun matchesDatabaseMapper(match: Match): MatchDatabaseEntity {
        return MatchDatabaseEntity(
            match.id,
            Competition(
                match.competition.name
            ),
            Team(
                match.homeTeam.name,
                match.homeTeam.crest
            ),
            Team(
                match.awayTeam.name,
                match.awayTeam.crest
            ),
            Score(
                Time(
                    match.score.halfTime.home,
                    match.score.halfTime.away
                ),
                Time(
                    match.score.fullTime.home,
                    match.score.fullTime.away
                ),
                match.score.winner
            ),
            match.status,
            match.utcDate
        )
    }

    fun matchesDatabaseToDataMapper(entity: MatchDatabaseEntity): Match {
        return Match(
            entity.dbId,
            Competition(
                entity.dbCompetition.name
            ),
            Team(
                entity.dbHomeTeam.name,
                EMPTY_STRING
            ),
            Team(
                entity.dbAwayTeam.name,
                EMPTY_STRING
            ),
            Score(
                Time(
                    entity.dbScore.halfTime.home,
                    entity.dbScore.halfTime.away
                ),
                Time(
                    entity.dbScore.fullTime.home,
                    entity.dbScore.fullTime.away
                ),
                EMPTY_STRING
            ),
            entity.dbStatus,
            entity.dbUtcDate
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}