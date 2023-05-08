package com.example.footballproject.ui.leagues

import androidx.recyclerview.widget.RecyclerView
import com.example.footballproject.CoilImageLoader.loadImage
import com.example.footballproject.R
import com.example.footballproject.databinding.RvLeaguesBinding
import com.example.footballproject.ui.models.leagues.CompetitionView

class LeaguesViewHolder(
    private val binding: RvLeaguesBinding,
    private val onItemClicked: (CompetitionView) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(competitionView: CompetitionView) {
        with(binding) {
            itemView.setOnClickListener {
                onItemClicked(competitionView)
            }
            if (competitionView.emblem.isNotEmpty()) {
                ivLeaguesImage.loadImage(competitionView.emblem)
            } else {
                ivLeaguesImage.setImageResource(R.drawable.ic_ball)
            }
            tvLeaguesName.text = competitionView.name
        }
    }
}