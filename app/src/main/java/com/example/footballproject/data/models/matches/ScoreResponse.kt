package com.example.footballproject.data.models.matches

import com.google.gson.annotations.SerializedName

data class ScoreResponse(
    @SerializedName("halfTime") val halfTime: TimeResponse? = null,
    @SerializedName("fullTime") val fullTime: TimeResponse? = null,
    @SerializedName("winner") val winner: String? = null
)