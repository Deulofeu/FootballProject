package com.example.footballproject.data.model.matches

import com.google.gson.annotations.SerializedName

data class AwayTeamResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("shortName") val shortName: String? = null,
    @SerializedName("tla") val tla: String? = null,
    @SerializedName("crest") val crest: String? = null
)
