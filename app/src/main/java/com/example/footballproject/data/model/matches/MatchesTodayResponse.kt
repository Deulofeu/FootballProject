package com.example.footballproject.data.model.matches

import com.google.gson.annotations.SerializedName

data class MatchesTodayResponse(
    @SerializedName("matches") val matches: List<MatchesResponse>? = null
)
