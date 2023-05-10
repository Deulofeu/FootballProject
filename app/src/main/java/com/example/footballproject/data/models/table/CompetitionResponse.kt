package com.example.footballproject.data.models.table

import com.google.gson.annotations.SerializedName

data class CompetitionResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("code") val code: String? = null
)
