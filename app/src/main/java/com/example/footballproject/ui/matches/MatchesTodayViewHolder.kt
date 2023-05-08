package com.example.footballproject.ui.matches

import androidx.recyclerview.widget.RecyclerView
import com.example.footballproject.CoilImageLoader.loadImage
import com.example.footballproject.databinding.RvMatchesBinding
import com.example.footballproject.ui.models.matches.MatchViewState

class MatchesTodayViewHolder(
    private val binding: RvMatchesBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(matchViewState: MatchViewState) {
        with(binding) {
            tvCompetition.text = matchViewState.competition.name
            ivHomeTeamEmblem.loadImage(matchViewState.homeTeam.crest)
            ivAwayTeamEmblem.loadImage(matchViewState.awayTeam.crest)
            tvHomeTeamName.text = matchViewState.homeTeam.name
            tvAwayTeamName.text = matchViewState.awayTeam.name

            when (matchViewState.status) {
                TIMED -> {
                    tvMatchDate.text = matchViewState.utcDate
                    tvHomeTeamScore.text = "-"
                    tvAwayTeamScore.text = "-"
                }
                IN_PLAY -> {
                    tvMatchDate.text = IN_PLAY_MATCH
                    tvHomeTeamScore.text = matchViewState.score.fullTime.home.toString()
                    tvAwayTeamScore.text = matchViewState.score.fullTime.away.toString()
                }
                PAUSED -> {
                    tvMatchDate.text = FIRST_HALF
                    tvHomeTeamScore.text = matchViewState.score.fullTime.home.toString()
                    tvAwayTeamScore.text = matchViewState.score.fullTime.away.toString()
                }
                FINISHED -> {
                    tvMatchDate.text = FINISHED
                    tvHomeTeamScore.text = matchViewState.score.fullTime.home.toString()
                    tvAwayTeamScore.text = matchViewState.score.fullTime.away.toString()
                }
                POSTPONED -> {
                    tvMatchDate.text = POSTPONED
                    tvHomeTeamScore.text = "-"
                    tvAwayTeamScore.text = "-"
                }
                SCHEDULED -> {
                    tvMatchDate.text = matchViewState.utcDate
                    tvHomeTeamScore.text = "-"
                    tvAwayTeamScore.text = "-"
                }
                else -> {
                    tvMatchDate.text = "-"
                    tvHomeTeamScore.text = "-"
                    tvAwayTeamScore.text = "-"
                }
            }
        }
    }

    companion object {
        const val TIMED: String = "TIMED"
        const val PAUSED: String = "PAUSED"
        const val FINISHED: String = "FINISHED"
        const val POSTPONED: String = "POSTPONED"
        const val SCHEDULED: String = "SCHEDULED"
        const val FIRST_HALF: String = "FIRST HALF"
        const val IN_PLAY: String = "IN_PLAY"
        const val IN_PLAY_MATCH: String = "IN PLAY"
    }
}