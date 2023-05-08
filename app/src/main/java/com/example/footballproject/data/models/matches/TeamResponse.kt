package com.example.footballproject.data.models.matches

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("crest") val crest: String? = null
)
