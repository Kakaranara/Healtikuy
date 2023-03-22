package com.kocci.healtikuy.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.constant.GameRules
import com.kocci.healtikuy.core.domain.HealthyStatusIndicator
import com.kocci.healtikuy.databinding.FragmentHomeBinding
import com.kocci.healtikuy.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAppBarWithMenuDrawer()
        bindClickListener()


        viewModel.healthyStatus.observe(viewLifecycleOwner) { healthyStatusIndicator ->
            val progressStatus = viewModel.calculateStatusPercentage(healthyStatusIndicator.point)
            val pointView = getString(
                R.string.status_points,
                healthyStatusIndicator.point,
                GameRules.MAX_STATUS_POINTS
            )
            binding.apply {
                statusIndicator.setProgressCompat(progressStatus, true)
                binding.tvPoint.text = pointView
            }

            when (healthyStatusIndicator) {
                is HealthyStatusIndicator.COMPLETED -> {
                    showToast("completed : ${healthyStatusIndicator.point}")
                }
                is HealthyStatusIndicator.NEARLY_COMPLETE -> {
                    showToast("nearly complete ${healthyStatusIndicator.point}")
                }
                is HealthyStatusIndicator.ZERO -> {
                    showToast("ZERO POINTS ${healthyStatusIndicator.point}")
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnWaterIntake -> {
                val directions = HomeFragmentDirections.actionHomeFragmentToWaterIntakeFragment()
                findNavController().navigate(directions)
            }
            binding.btnSleep -> {
                val directions = HomeFragmentDirections.actionHomeFragmentToSleepFragment()
                findNavController().navigate(directions)
            }
            binding.btnExercise -> {
                val directions = HomeFragmentDirections.actionHomeFragmentToExerciseFragment()
                findNavController().navigate(directions)
            }
            binding.btnNutrition -> {
                val directions = HomeFragmentDirections.actionHomeFragmentToNutritionFragment()
                findNavController().navigate(directions)
            }
            binding.btnSunExposure -> {
                val directions = HomeFragmentDirections.actionHomeFragmentToSunExposureFragment()
                findNavController().navigate(directions)
            }
        }
    }

    private fun bindClickListener() {
        binding.btnExercise.setOnClickListener(this)
        binding.btnNutrition.setOnClickListener(this)
        binding.btnSleep.setOnClickListener(this)
        binding.btnSunExposure.setOnClickListener(this)
        binding.btnWaterIntake.setOnClickListener(this)
    }

    private fun setupAppBarWithMenuDrawer() {
        val navController = findNavController()
        val drawerLayout = activity?.findViewById<DrawerLayout>(R.id.drawer_layout)
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}