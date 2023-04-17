package com.example.footballproject.domain.leagues

sealed class LeaguesView {
    object Loading : LeaguesView()
    object Error : LeaguesView()
    data class ContentLeagues(val leagues: List<CompetitionView>) : LeaguesView()
}