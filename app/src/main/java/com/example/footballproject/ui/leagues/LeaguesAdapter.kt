package com.example.footballproject.ui.leagues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.footballproject.CoilImageLoader.loadImage
import com.example.footballproject.databinding.RvLeaguesBinding
import com.example.footballproject.domain.leagues.CompetitionView

class LeaguesAdapter(
    val onItemClicked: (CompetitionView) -> Unit
) : RecyclerView.Adapter<LeaguesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RvLeaguesBinding) :
        RecyclerView.ViewHolder(binding.root) {
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

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvLeaguesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    companion object {

        private val differCallback = object : DiffUtil.ItemCallback<CompetitionView>() {
            override fun areItemsTheSame(
                oldItem: CompetitionView,
                newItem: CompetitionView
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CompetitionView,
                newItem: CompetitionView
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}