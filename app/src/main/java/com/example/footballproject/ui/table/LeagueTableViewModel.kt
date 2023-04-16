package com.example.footballproject.ui.table

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballproject.Result
import com.example.footballproject.data.mappers.table.LeagueTableMapper
import com.example.footballproject.domain.FootballRepository
import com.example.footballproject.domain.table.LeagueTableView
import com.example.footballproject.domain.table.StandingView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeagueTableViewModel @Inject constructor(
    private val repository: FootballRepository,
    private val mapper: LeagueTableMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
): ViewModel() {

    private val _viewLeagueTable = MutableLiveData<LeagueTableView>()
    val viewLeagueTable: LiveData<LeagueTableView> get() = _viewLeagueTable

    fun getLeagueTable(code: String) = viewModelScope.launch(dispatcher) {
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
            delay(60000)
        }
    }
}