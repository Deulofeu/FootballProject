package com.example.footballproject.ui.table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.footballproject.databinding.RvTableBinding
import com.example.footballproject.domain.table.TableView

class LeagueTableAdapter(
) : RecyclerView.Adapter<LeagueTableViewHolder>() {

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueTableViewHolder {
        return LeagueTableViewHolder(
            RvTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: LeagueTableViewHolder, position: Int) {
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