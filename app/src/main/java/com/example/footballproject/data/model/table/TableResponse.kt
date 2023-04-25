package com.example.footballproject.data.model.table

import com.google.gson.annotations.SerializedName

data class TableResponse(
    @SerializedName("position") val position: Int? = null,
    @SerializedName("team") val team: TeamResponse? = null,
    @SerializedName("playedGames") val playedGames: Int? = null,
    @SerializedName("won") val won: Int? = null,
    @SerializedName("draw") val draw: Int? = null,
    @SerializedName("lost") val lost: Int? = null,
    @SerializedName("form") val form: String? = null,
    @SerializedName("goalDifference") val goalDifference: Int? = null,
    @SerializedName("points") val points: Int? = null,
)