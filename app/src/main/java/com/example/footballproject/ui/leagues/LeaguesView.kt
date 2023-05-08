package com.example.footballproject.ui.leagues

import com.example.footballproject.ui.models.leagues.CompetitionView

sealed class LeaguesView {
    object Loading : LeaguesView()
    object Error : LeaguesView()
    data class ContentLeagues(val leagues: List<CompetitionView>) : LeaguesView()
}