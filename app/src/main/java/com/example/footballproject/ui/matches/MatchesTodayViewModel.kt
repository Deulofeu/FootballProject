package com.example.footballproject.ui.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballproject.R
import com.example.footballproject.domain.FootballRepository
import com.example.footballproject.domain.matches.MatchesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.footballproject.Result
import com.example.footballproject.data.mappers.matches.MatchesMapper
import kotlinx.coroutines.CoroutineExceptionHandler

@HiltViewModel
class MatchesTodayViewModel @Inject constructor(
    private val repository: FootballRepository,
    private val matchesMapper: MatchesMapper
) : ViewModel() {

    private val _viewMatchesToday = MutableLiveData<MatchesTodayViewState>()
    val viewMatchesToday: LiveData<MatchesTodayViewState> get() = _viewMatchesToday

    private val _errorViewMatches = MutableLiveData<Int>()
    val errorViewMatches: LiveData<Int> get() = _errorViewMatches

    private val exceptionHandler = CoroutineExceptionHandler { _, ex ->
        _errorViewMatches.value = R.string.unknown_error
    }

    fun getMatchesToday() = viewModelScope.launch(exceptionHandler) {
        _viewMatchesToday.postValue(MatchesTodayViewState.Loading)
        while (true) {
            when (val result = repository.getMatches()) {
                is Result.Error -> {
                    _viewMatchesToday.postValue(MatchesTodayViewState.Error)
                }
                is Result.Success -> {
                    val matches = MatchesViewState(
                        result.data.matches.map {
                            it.utcDate = getDate(it.utcDate)!!
                            matchesMapper.matchToMatchView(it)
                        }.toList()
                    )
                    _viewMatchesToday.postValue(
                        MatchesTodayViewState.ContentMatchesToday(
                            matches
                        )
                    )
                }
            }
            delay(15000)
        }
    }

    private fun getDate(dateString: String?): String? {
        return dateString?.split("T")?.get(0)
    }
}