package com.example.footballproject.data.model.leagues

import com.google.gson.annotations.SerializedName

data class LeaguesResponse(
    @SerializedName("competitions") val competitions: List<CompetitionResponse>? = null
)