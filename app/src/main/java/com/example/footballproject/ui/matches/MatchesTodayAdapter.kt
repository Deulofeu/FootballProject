package com.example.footballproject.ui.matches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.footballproject.databinding.RvMatchesBinding
import com.example.footballproject.domain.matches.MatchViewState

class MatchesTodayAdapter(
) : RecyclerView.Adapter<MatchesTodayViewHolder>() {

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesTodayViewHolder {
        return MatchesTodayViewHolder(
            RvMatchesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MatchesTodayViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<MatchViewState>() {
            override fun areItemsTheSame(
                oldItem: MatchViewState,
                newItem: MatchViewState
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MatchViewState,
                newItem: MatchViewState
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}