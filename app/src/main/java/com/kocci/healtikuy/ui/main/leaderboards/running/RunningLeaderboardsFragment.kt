package com.kocci.healtikuy.ui.main.leaderboards.running

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.databinding.FragmentRunningLeaderboardsBinding
import com.kocci.healtikuy.util.extension.showToast


class RunningLeaderboardsFragment : Fragment() {
    private var _binding: FragmentRunningLeaderboardsBinding? = null
    private val binding get() = _binding!!
    val args by navArgs<RunningLeaderboardsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarRunningLeaderboards.setupWithNavController(findNavController())
        val data = args.list

        showToast(data.toString())
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