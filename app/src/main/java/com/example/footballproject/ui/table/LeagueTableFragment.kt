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
import com.example.footballproject.utils.CoilImageLoader.loadImage
import com.example.footballproject.R
import com.example.footballproject.databinding.LeagueTableFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeagueTableFragment : Fragment() {

    private var _binding: LeagueTableFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LeagueTableViewModel by viewModels()
    private val tablesAdapter = LeagueTableAdapter()
    private val args: LeagueTableFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LeagueTableFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNetworkConnection()
        setupRecyclerView()
        initArgs()

        viewModel.viewLeagueTable.observe(viewLifecycleOwner) { standings ->
            when (standings) {
                is LeagueTableView.ContentLeagueTable -> {
                    if (standings.standings.isEmpty()) {
                        binding.clTable.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.ivSad.visibility = View.VISIBLE
                        binding.textMessage.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.GONE
                        binding.ivSad.visibility = View.GONE
                        binding.textMessage.visibility = View.GONE
                        if (standings.standings[0].stage == GROUP_STAGE) {
                            Toast.makeText(context, R.string.update, Toast.LENGTH_SHORT).show()
                        } else {
                            tablesAdapter.differ.submitList(standings.standings[0].table)
                        }
                    }
                }
                is LeagueTableView.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun checkNetworkConnection() {
        viewModel.checkNetworkConnection()
        viewModel.checkNetworkLiveData.observe(viewLifecycleOwner) { checkNetwork ->
            if (!checkNetwork)
                Toast.makeText(
                    context, getString(R.string.connection_error), Toast.LENGTH_SHORT
                ).show()
        }
    }

    private fun setupRecyclerView() {
        binding.rvTable.adapter = tablesAdapter
        binding.rvTable.layoutManager = LinearLayoutManager(activity)
    }

    private fun initArgs() {
        args.let {
            binding.ivLeagueEmblem.loadImage(it.leagueEmblem)
            binding.tvLeagueTitle.text = it.leagueName
            viewModel.getLeagueTable(it.leagueCode)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val GROUP_STAGE: String = "GROUP_STAGE"
    }
}