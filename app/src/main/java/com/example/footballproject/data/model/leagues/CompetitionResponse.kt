package com.example.footballproject.data.model.leagues

import com.google.gson.annotations.SerializedName

data class CompetitionResponse(
    @SerializedName("code") val code: String? = null,
    @SerializedName("emblem") var emblem: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") val type: String? = null
)