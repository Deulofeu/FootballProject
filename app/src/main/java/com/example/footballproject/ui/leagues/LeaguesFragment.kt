package com.example.footballproject.ui.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballproject.databinding.LeaguesFragmentBinding
import com.example.footballproject.domain.leagues.CompetitionView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaguesFragment : Fragment() {

    private var _binding: LeaguesFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LeaguesViewModel by viewModels()
    private val leaguesAdapter = LeaguesAdapter(::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LeaguesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.viewLeagues.observe(viewLifecycleOwner) { leagues ->
            when (leagues) {
                is LeaguesView.ContentLeagues -> {
                    binding.progressBar.visibility = View.GONE
                    val list: List<CompetitionView> = leagues.leagues
                    leaguesAdapter.differ.submitList(list)
                }
                is LeaguesView.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
                is LeaguesView.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
        viewModel.getLeagues()
    }

    private fun onItemClicked(viewState: CompetitionView) {
        findNavController().navigate(
            LeaguesFragmentDirections.actionLeaguesFragmentToLeagueTableFragment(
                viewState.code,
                viewState.name,
                viewState.emblem
            )
        )
    }

    private fun setupRecyclerView() {
        binding.rvLeagues.adapter = leaguesAdapter
        binding.rvLeagues.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}