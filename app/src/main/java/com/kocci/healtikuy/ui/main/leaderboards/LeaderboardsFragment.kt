package com.kocci.healtikuy.ui.main.leaderboards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocci.healtikuy.core.util.store.Avatar
import com.kocci.healtikuy.databinding.FragmentLeaderboardsBinding

class LeaderboardsFragment : Fragment() {
    private var _binding: FragmentLeaderboardsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LeaderboardsVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupAdapter()
    }

    private fun setupAdapter() {
        val mAdapter = LeadPointsAdapter(generateList(), requireActivity())
        val manager = LinearLayoutManager(requireActivity())
        binding.rvLeaderboards.apply {
            adapter = mAdapter
            layoutManager = manager
        }
    }

    private fun generateList(): List<LeaderPoints> {
        return List(5) {
            LeaderPoints("hey $it", Avatar.FINN.lowerNames, 5000, it)
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