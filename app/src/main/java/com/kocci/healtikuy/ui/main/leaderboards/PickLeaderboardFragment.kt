package com.kocci.healtikuy.ui.main.leaderboards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.databinding.FragmentPickLeaderboardBinding

class PickLeaderboardFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentPickLeaderboardBinding? = null
    private val binding get() = _binding!!
    val args by navArgs<PickLeaderboardFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarPickLeaderboard.setupWithNavController(findNavController())
        binding.btnToRunningLeaderboards.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnToRunningLeaderboards -> {
                val direction =
                    PickLeaderboardFragmentDirections.actionPickLeaderboardFragmentToRunningLeaderboardsFragment(
                        args.list
                    )
                findNavController().navigate(direction)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPickLeaderboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}