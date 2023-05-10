package com.example.footballproject.domain.table

data class LeagueTable(
    val id: Int,
    val code: String,
    val standings: List<Standing>
)