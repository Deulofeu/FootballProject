package com.example.footballproject.domain.table

sealed class LeagueTableView {
    object Loading : LeagueTableView()
    object Error : LeagueTableView()
    data class ContentLeagueTable(
        val standings: MutableList<StandingView>) : LeagueTableView()
}