package com.example.footballproject.ui.table

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballproject.R
import com.example.footballproject.Result
import com.example.footballproject.data.mappers.table.LeagueTableMapper
import com.example.footballproject.domain.FootballRepository
import com.example.footballproject.domain.table.StandingView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class LeagueTableViewModel @Inject constructor(
    private val repository: FootballRepository,
    private val mapper: LeagueTableMapper,
): ViewModel() {

    private val _viewLeagueTable = MutableLiveData<LeagueTableView>()
    val viewLeagueTable: LiveData<LeagueTableView> get() = _viewLeagueTable

    private val _errorViewLeagues = MutableLiveData<Int>()
    val errorViewLeagues: LiveData<Int> get() = _errorViewLeagues

    private val exceptionHandler = CoroutineExceptionHandler { _, ex ->
        _errorViewLeagues.value = R.string.unknown_error
    }

    fun getLeagueTable(code: String) = viewModelScope.launch(exceptionHandler) {
        _viewLeagueTable.postValue(LeagueTableView.Loading)
        while (true) {
            when (val result = repository.getLeagueTable(code)) {
                is Result.Error -> {
                    _viewLeagueTable.postValue(LeagueTableView.Error)
                }
                is Result.Success -> {
                    val table = result.data.standings.map { standing ->
                        StandingView(
                            standing.stage,
                            standing.table.map { table ->
                                mapper.tableToTableViewStateMapper(table)
                            }
                        )
                    }.toMutableList()
                    _viewLeagueTable.postValue(
                        LeagueTableView.ContentLeagueTable(
                            table
                        ))
                }
            }
            delay(6000)
        }
    }
}