package com.example.footballproject.ui.matches

import com.example.footballproject.domain.matches.MatchesViewState

sealed class MatchesTodayViewState {
    object Loading : MatchesTodayViewState()
    object Error : MatchesTodayViewState()
    data class ContentMatchesToday(
        val matchesToday: MatchesViewState
    ) : MatchesTodayViewState()
}