package com.kocci.healtikuy.ui.main.leaderboards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsAttr
import com.kocci.healtikuy.databinding.FragmentLeaderboardsBinding
import com.kocci.healtikuy.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaderboardsFragment : Fragment() {
    private var _binding: FragmentLeaderboardsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LeaderboardsVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()

        viewModel.getData().observe(viewLifecycleOwner) {
            when (it) {
                Async.Loading -> {
                    showToast("Loading..")
                }

                is Async.Error -> {
                    showToast("Error ${it.msg}")
                }

                is Async.Success -> {
                    setupAdapter(it.data)
                }
            }
        }
    }

    private fun setupAdapter(data: List<LeaderboardsAttr>) {
        val listOfPoints = viewModel.getSortedData(data)
        val mAdapter = LeadPointsAdapter(listOfPoints, requireActivity())
        val manager = LinearLayoutManager(requireActivity())
        binding.rvLeaderboards.apply {
            adapter = mAdapter
            layoutManager = manager
        }
    }

    private fun setupToolbar() {
        binding.toolbarLeaderboards.setupWithNavController(findNavController())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLeaderboardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}