package com.example.footballproject.data.models.matches

import com.google.gson.annotations.SerializedName

data class TimeResponse(
    @SerializedName("home") val home: Int? = null,
    @SerializedName("away") val away: Int? = null
)