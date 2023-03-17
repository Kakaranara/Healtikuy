package com.kocci.healtikuy.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAppBarWithMenuDrawer()

        binding.statusIndicator.setProgressCompat(50, true)
        binding.btnExercise.setOnClickListener(this)
        binding.btnNutrition.setOnClickListener(this)
        binding.btnSleep.setOnClickListener(this)
        binding.btnSunExposure.setOnClickListener(this)
        binding.btnWaterIntake.setOnClickListener(this)
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