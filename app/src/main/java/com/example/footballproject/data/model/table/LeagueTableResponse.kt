package com.example.footballproject.data.model.table

import com.google.gson.annotations.SerializedName

data class LeagueTableResponse(
    @SerializedName("standings") val standings: List<StandingResponse>
)