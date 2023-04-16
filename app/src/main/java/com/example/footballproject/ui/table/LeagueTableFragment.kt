package com.example.footballproject.ui.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballproject.R
import com.example.footballproject.databinding.LeagueTableFragmentBinding
import com.example.footballproject.domain.table.LeagueTableView
import com.example.footballproject.CoilImageLoader.loadImage
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class LeagueTableFragment : Fragment() {

    private var _binding: LeagueTableFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LeagueTableViewModel by viewModels()
    private val tablesAdapter = LeagueTableAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LeagueTableFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTable.adapter = tablesAdapter
        binding.rvTable.layoutManager = LinearLayoutManager(activity)

        val args: LeagueTableFragmentArgs by navArgs()
        args.let {
            try {
                binding.ivLeagueEmblem.loadImage(it.leagueEmblem)
                binding.tvLeagueTitle.text = it.leagueName
                viewModel.getLeagueTable(it.leagueCode)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        viewModel.viewLeagueTable.observe(viewLifecycleOwner) { standings ->
            when (standings) {
                is LeagueTableView.ContentLeagueTable -> {
                    binding.progressBar.visibility = View.GONE
                    if (standings.standings[0].stage == "GROUP_STAGE") {
                        Toast.makeText(context, "information will be added later", Toast.LENGTH_SHORT).show()
                    } else {
                        tablesAdapter.differ.submitList(standings.standings[0].table)
                    }
                }
                is LeagueTableView.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
                is LeagueTableView.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        binding.tvName.text = resources.getString(R.string.team)
        binding.tvPlayed.text = resources.getString(R.string.played)
        binding.tvWon.text = resources.getString(R.string.won)
        binding.tvDrawn.text = resources.getString(R.string.drawn)
        binding.tvLost.text = resources.getString(R.string.lost)
        binding.tvPoints.text = resources.getString(R.string.points)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}