package com.example.footballproject.data.model.matches

import com.google.gson.annotations.SerializedName

data class CompetitionResponse(
    @SerializedName("code") val code: String? = null,
    @SerializedName("emblem") val emblem: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("type") val type: String? = null
)
