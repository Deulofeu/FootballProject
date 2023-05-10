package com.example.footballproject.data.models.table

import com.google.gson.annotations.SerializedName

data class LeagueTableResponse(
    @SerializedName("competition") val competition: CompetitionResponse? = null,
    @SerializedName("standings") val standings: List<StandingResponse>? = null
)