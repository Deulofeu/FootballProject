package com.example.footballproject.ui.leagues

import androidx.recyclerview.widget.RecyclerView
import com.example.footballproject.CoilImageLoader.loadImage
import com.example.footballproject.databinding.RvLeaguesBinding
import com.example.footballproject.domain.leagues.CompetitionView

class LeaguesViewHolder(
    private val binding: RvLeaguesBinding,
    private val onItemClicked: (CompetitionView) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(competitionXViewState: CompetitionView) {
        with(binding) {
            itemView.setOnClickListener {
                onItemClicked(competitionXViewState)
            }
            ivLeaguesImage.loadImage(competitionXViewState.emblem)
            tvLeaguesName.text = competitionXViewState.name
        }
    }
}