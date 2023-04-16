package com.example.footballproject.data.model.table

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("crest") val crest: String,
    @SerializedName("name") val name: String
)