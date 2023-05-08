package com.example.footballproject.ui.table

import com.example.footballproject.ui.models.table.StandingView

sealed class LeagueTableView {
    object Loading : LeagueTableView()
    object Error : LeagueTableView()
    data class ContentLeagueTable(
        val standings: MutableList<StandingView>) : LeagueTableView()
}