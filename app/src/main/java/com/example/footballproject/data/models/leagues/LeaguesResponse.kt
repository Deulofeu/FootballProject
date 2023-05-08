package com.example.footballproject.data.models.leagues

import com.google.gson.annotations.SerializedName

data class LeaguesResponse(
    @SerializedName("competitions") val competitions: List<CompetitionResponse>? = null
)