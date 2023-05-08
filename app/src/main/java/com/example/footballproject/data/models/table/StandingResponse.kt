package com.example.footballproject.data.models.table

import com.google.gson.annotations.SerializedName

data class StandingResponse(
    @SerializedName("stage") val stage: String? = null,
    @SerializedName("table") val table: List<TableResponse>? = null
)