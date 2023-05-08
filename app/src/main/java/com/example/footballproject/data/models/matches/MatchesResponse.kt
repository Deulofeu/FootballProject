package com.example.footballproject.data.models.matches

import com.google.gson.annotations.SerializedName

data class MatchesResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("competition") val competition: CompetitionResponse? = null,
    @SerializedName("homeTeam") val homeTeam: TeamResponse? = null,
    @SerializedName("awayTeam") val awayTeam: TeamResponse? = null,
    @SerializedName("score") val score: ScoreResponse? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("utcDate") val utcDate: String? = null
)