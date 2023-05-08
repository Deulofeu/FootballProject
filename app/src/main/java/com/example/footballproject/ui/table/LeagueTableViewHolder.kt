package com.example.footballproject.ui.table

import androidx.recyclerview.widget.RecyclerView
import com.example.footballproject.utils.CoilImageLoader.loadImage
import com.example.footballproject.databinding.RvTableBinding
import com.example.footballproject.ui.models.table.TableView

class LeagueTableViewHolder(
    private val binding: RvTableBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(tableView: TableView) {
        with(binding) {
            tvPosition.text = tableView.position.toString()
            ivEmblem.loadImage(tableView.team.crest)
            tvName.text = tableView.team.name
            tvPlayed.text = tableView.playedGames.toString()
            tvWon.text = tableView.won.toString()
            tvDrawn.text = tableView.draw.toString()
            tvLost.text = tableView.lost.toString()
            tvGoalsDifference.text = tableView.goalDifference.toString()
            tvPoints.text = tableView.points.toString()
        }
    }
}