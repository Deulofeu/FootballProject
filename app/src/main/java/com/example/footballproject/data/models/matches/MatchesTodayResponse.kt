package com.example.footballproject.data.models.matches

import com.google.gson.annotations.SerializedName

data class MatchesTodayResponse(
    @SerializedName("matches") val matches: List<MatchesResponse>? = null
)
