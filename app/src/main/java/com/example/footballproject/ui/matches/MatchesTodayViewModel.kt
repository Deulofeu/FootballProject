package com.example.footballproject.ui.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballproject.R
import com.example.footballproject.Result
import com.example.footballproject.domain.FootballRepository
import com.example.footballproject.ui.mappers.matches.MatchesMapperUI
import com.example.footballproject.ui.models.matches.MatchesViewState
import com.example.footballproject.utils.CheckNetworkConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MatchesTodayViewModel @Inject constructor(
    private val checkNetworkConnection: CheckNetworkConnection,
    private val repository: FootballRepository,
    private val matchesMapper: MatchesMapperUI
) : ViewModel() {

    private val _checkNetworkLiveData = MutableLiveData<Boolean>()
    val checkNetworkLiveData: LiveData<Boolean> get() = _checkNetworkLiveData

    private val _viewMatchesToday = MutableLiveData<MatchesTodayView>()
    val viewMatchesToday: LiveData<MatchesTodayView> get() = _viewMatchesToday

    private val _errorViewMatches = MutableLiveData<Int>()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _errorViewMatches.value = R.string.unknown_error
    }

    fun getMatchesToday() = viewModelScope.launch(exceptionHandler) {
        _viewMatchesToday.postValue(MatchesTodayView.Loading)
        when (val result = repository.getMatches()) {
            is Result.Success -> {
                val matches = MatchesViewState(
                    result.data.matches.map {
                        matchesMapper.matchToMatchViewMapper(it)
                    }.toList().sortedWith(
                        compareBy(
                            { it.status },
                            { it.competition.name },
                            { it.homeTeam.name })
                    )
                )
                _viewMatchesToday.postValue(
                    MatchesTodayView.ContentMatchesToday(
                        matches
                    )
                )
            }
        }
    }

    fun checkNetworkConnection() {
        _checkNetworkLiveData.value = checkNetworkConnection.isInternetAvailable()
    }
}