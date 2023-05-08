package com.example.footballproject.data.models.matches

import com.google.gson.annotations.SerializedName

data class CompetitionResponse(
    @SerializedName("name") val name: String? = null
)
