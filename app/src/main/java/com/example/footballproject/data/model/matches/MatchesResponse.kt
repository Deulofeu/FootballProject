package com.example.footballproject.data.model.matches

import com.google.gson.annotations.SerializedName

data class MatchesResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("competition") val competition: CompetitionResponse? = null,
    @SerializedName("homeTeam") val homeTeam: HomeTeamResponse? = null,
    @SerializedName("awayTeam") val awayTeam: AwayTeamResponse? = null,
    @SerializedName("score") val score: ScoreResponse? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("utcDate") val utcDate: String? = null
)