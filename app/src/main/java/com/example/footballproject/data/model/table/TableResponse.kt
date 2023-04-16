package com.example.footballproject.data.model.table

import com.google.gson.annotations.SerializedName

data class TableResponse(
    @SerializedName("position") val position: Int,
    @SerializedName("team") val team: TeamResponse,
    @SerializedName("playedGames") val playedGames: Int,
    @SerializedName("won") val won: Int,
    @SerializedName("draw") val draw: Int,
    @SerializedName("lost") val lost: Int,
    @SerializedName("form") val form: String,
    @SerializedName("goalDifference") val goalDifference: Int,
    @SerializedName("points") val points: Int,
)