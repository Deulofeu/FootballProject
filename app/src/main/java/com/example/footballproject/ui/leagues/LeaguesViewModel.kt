package com.example.footballproject.ui.leagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballproject.R
import com.example.footballproject.Result
import com.example.footballproject.domain.FootballRepository
import com.example.footballproject.ui.mappers.leagues.LeaguesMapperUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaguesViewModel @Inject constructor(
    private val repository: FootballRepository,
    private val mapper: LeaguesMapperUI,
) : ViewModel() {

    private val _viewLeagues = MutableLiveData<LeaguesView>()
    val viewLeagues: LiveData<LeaguesView> get() = _viewLeagues
    private val _errorViewLeagues = MutableLiveData<Int>()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _errorViewLeagues.value = R.string.unknown_error
    }

    fun getLeagues() = viewModelScope.launch(exceptionHandler) {
        _viewLeagues.postValue(LeaguesView.Loading)
        when (val result = repository.getLeagues()) {
            is Result.Error -> {
                _viewLeagues.postValue(LeaguesView.Error)
            }
            is Result.Success -> {
                val leagues = result.data.competitions.map { league ->
                    mapper.competitionToCompetitionViewMapper(league)
                }.toList()
                _viewLeagues.postValue(
                    leagues.let { LeaguesView.ContentLeagues(it) }
                )
            }
        }
    }
}