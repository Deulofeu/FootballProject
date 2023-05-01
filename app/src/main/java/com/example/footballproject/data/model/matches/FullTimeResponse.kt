package com.example.footballproject.data.model.matches

import com.google.gson.annotations.SerializedName

data class FullTimeResponse(
    @SerializedName("home") val home: Int? = null,
    @SerializedName("away") val away: Int? = null,
)