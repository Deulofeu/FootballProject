package com.example.footballproject.ui.table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.footballproject.CoilImageLoader.loadImage
import com.example.footballproject.databinding.RvTableBinding
import com.example.footballproject.domain.table.TableView

class LeagueTableAdapter(
) : RecyclerView.Adapter<LeagueTableAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RvTableBinding) :
        RecyclerView.ViewHolder(binding.root) {
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

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<TableView>() {

            override fun areItemsTheSame(oldItem: TableView, newItem: TableView): Boolean {
                return oldItem.position == newItem.position
            }
            override fun areContentsTheSame(oldItem: TableView, newItem: TableView): Boolean {
                return oldItem == newItem
            }
        }
    }
}