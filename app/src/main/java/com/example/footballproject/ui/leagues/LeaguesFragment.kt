package com.example.footballproject.ui.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballproject.R
import com.example.footballproject.databinding.LeaguesFragmentBinding
import com.example.footballproject.ui.models.leagues.CompetitionView
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

        checkNetworkConnection()
        setupRecyclerView()

        viewModel.viewLeagues.observe(viewLifecycleOwner) { leagues ->
            when (leagues) {
                is LeaguesView.ContentLeagues -> {
                    if (leagues.leagues.isEmpty()) {
                        binding.progressBar.visibility = View.GONE
                        binding.ivSad.visibility = View.VISIBLE
                        binding.textMessage.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.GONE
                        binding.ivSad.visibility = View.GONE
                        binding.textMessage.visibility = View.GONE
                        leaguesAdapter.differ.submitList(leagues.leagues)
                    }
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
        binding.rvLeagues.adapter = leaguesAdapter
        binding.rvLeagues.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}