package com.example.footballproject.ui.matches

import com.example.footballproject.ui.models.matches.MatchesViewState

sealed class MatchesTodayView {
    object Loading : MatchesTodayView()
    object Error : MatchesTodayView()
    data class ContentMatchesToday(
        val matchesToday: MatchesViewState
    ) : MatchesTodayView()
}