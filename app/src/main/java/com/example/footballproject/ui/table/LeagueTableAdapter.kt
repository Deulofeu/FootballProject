package com.example.footballproject.ui.table

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.footballproject.R
import com.example.footballproject.databinding.RvTableBinding
import com.example.footballproject.domain.table.TableView
import com.example.footballproject.CoilImageLoader.loadImage

class LeagueTableAdapter(
): RecyclerView.Adapter<LeagueTableAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(tableViewState: TableView) {
            val bind = RvTableBinding.bind(itemView)
            bind.apply {
                tvPosition.text = tableViewState.position.toString()
                ivEmblem.loadImage(tableViewState.team.crest)
                tvName.text = tableViewState.team.name
                tvPlayed.text = tableViewState.playedGames.toString()
                tvWon.text = tableViewState.won.toString()
                tvDrawn.text = tableViewState.draw.toString()
                tvLost.text = tableViewState.lost.toString()
                tvGoalsDifference.text = tableViewState.goalDifference.toString()
                tvPoints.text = tableViewState.points.toString()
            }
        }
    }

    private val differCallback = object: DiffUtil.ItemCallback<TableView>() {
        override fun areItemsTheSame(oldItem: TableView, newItem: TableView): Boolean {
            return oldItem.position == newItem.position
        }

        override fun areContentsTheSame(oldItem: TableView, newItem: TableView): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_table, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}