package com.example.footballproject.data.model.matches

import com.google.gson.annotations.SerializedName

data class ScoreResponse(
    @SerializedName("halfTime") val halfTime: HalfTimeResponse? = null,
    @SerializedName("fullTime") val fullTime: FullTimeResponse? = null,
    @SerializedName("winner") val winner: String? = null
)