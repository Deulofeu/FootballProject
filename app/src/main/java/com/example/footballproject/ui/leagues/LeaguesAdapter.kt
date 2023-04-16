package com.example.footballproject.ui.leagues

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.footballproject.R
import com.example.footballproject.databinding.RvLeaguesBinding
import com.example.footballproject.domain.leagues.CompetitionView
import com.example.footballproject.CoilImageLoader.loadImage

class LeaguesAdapter(
    val onItemClicked: (CompetitionView) -> Unit
): RecyclerView.Adapter<LeaguesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(competitionXViewState: CompetitionView) {
            val bind = RvLeaguesBinding.bind(itemView)
            itemView.setOnClickListener {
                onItemClicked(competitionXViewState)
            }
            bind.apply {
                ivLeaguesImage.loadImage(competitionXViewState.emblem)
                tvLeaguesName.text = competitionXViewState.name
            }
        }
    }

    private val differCallback = object: DiffUtil.ItemCallback<CompetitionView>() {
        override fun areItemsTheSame(oldItem: CompetitionView, newItem: CompetitionView): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CompetitionView, newItem: CompetitionView): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_leagues, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}