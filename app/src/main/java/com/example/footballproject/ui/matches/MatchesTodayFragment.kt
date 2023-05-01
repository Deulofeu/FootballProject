package com.example.footballproject.ui.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballproject.databinding.MatchesTodayFragmentBinding
import com.example.footballproject.domain.matches.MatchViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchesTodayFragment : Fragment() {

    private var _binding: MatchesTodayFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MatchesTodayViewModel by viewModels()

    private var mMatchesTodayAdapter = MatchesTodayAdapter()
    private var mAvailableMatches = ArrayList<MatchViewState>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = MatchesTodayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.viewMatchesToday.observe(viewLifecycleOwner) { matches ->
            when (matches) {
                is MatchesTodayViewState.ContentMatchesToday -> {
                    binding.progressBar.visibility = View.GONE
                    mAvailableMatches = ArrayList()
                    for (match in matches.matchesToday.matches) {
                        mAvailableMatches.add(match)
                    }

                    val sorted: List<MatchViewState> =
                        if (matches.matchesToday.matches.isNotEmpty()) {
                            matches.matchesToday.matches.sortedWith(
                                compareBy({ it.status },
                                    { it.competition.name },
                                    { it.homeTeam.name })
                            )
                        } else {
                            listOf()
                        }
                    mMatchesTodayAdapter.differ.submitList(sorted)

                }
                is MatchesTodayViewState.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
                is MatchesTodayViewState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        viewModel.getMatchesToday()
    }

    private fun setupRecyclerView() {
        binding.rvMatches.adapter = mMatchesTodayAdapter
        binding.rvMatches.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}