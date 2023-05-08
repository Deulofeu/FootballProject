package com.example.footballproject.data.models.leagues

import com.google.gson.annotations.SerializedName

data class CompetitionResponse(
    @SerializedName("code") val code: String? = null,
    @SerializedName("emblem") val emblem: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null
)