package com.kocci.healtikuy.ui.main.leaderboards.running

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.kocci.healtikuy.databinding.FragmentRunningLeaderboardsBinding


class RunningLeaderboardsFragment : Fragment() {
    private var _binding: FragmentRunningLeaderboardsBinding? = null
    private val binding get() = _binding!!
    val args by navArgs<RunningLeaderboardsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarRunningLeaderboards.setupWithNavController(findNavController())

        val viewPager = binding.vpRunningLeaderboards
        val tabLayout = binding.tlRunningLeaderboards

        viewPager.adapter = RunningLeadStateAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "100M"
                }

                1 -> {
                    tab.text = "200M"
                }

                2 -> tab.text = "400M"
            }
        }.attach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRunningLeaderboardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}