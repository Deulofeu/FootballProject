package com.example.footballproject.ui.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballproject.R
import com.example.footballproject.databinding.MatchesTodayFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchesTodayFragment() : Fragment() {

    private var _binding: MatchesTodayFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MatchesTodayViewModel by viewModels()

    private val mMatchesTodayAdapter = MatchesTodayAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = MatchesTodayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNetworkConnection()
        setupRecyclerView()

        viewModel.viewMatchesToday.observe(viewLifecycleOwner) { matches ->
            when (matches) {
                is MatchesTodayView.ContentMatchesToday -> {
                    if (matches.matchesToday.matches.isEmpty()) {
                        binding.progressBar.visibility = View.GONE
                        binding.ivSad.visibility = View.VISIBLE
                        binding.textMessage.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.GONE
                        binding.ivSad.visibility = View.GONE
                        binding.textMessage.visibility = View.GONE
                        mMatchesTodayAdapter.differ.submitList(matches.matchesToday.matches)
                    }

                }
                is MatchesTodayView.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        viewModel.getMatchesToday()
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
        binding.rvMatches.adapter = mMatchesTodayAdapter
        binding.rvMatches.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}