package com.example.footballproject.ui.matches

import com.example.footballproject.domain.matches.MatchesViewState

sealed class MatchesTodayView {
    object Loading : MatchesTodayView()
    object Error : MatchesTodayView()
    data class ContentMatchesToday(
        val matchesToday: MatchesViewState
    ) : MatchesTodayView()
}