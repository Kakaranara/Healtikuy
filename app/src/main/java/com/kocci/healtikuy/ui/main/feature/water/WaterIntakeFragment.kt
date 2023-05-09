package com.kocci.healtikuy.ui.main.feature.water

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.constant.GameRules
import com.kocci.healtikuy.core.domain.model.WaterIntake
import com.kocci.healtikuy.core.util.helper.PointsManager
import com.kocci.healtikuy.databinding.FragmentWaterIntakeBinding
import com.kocci.healtikuy.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WaterIntakeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentWaterIntakeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WaterIntakeViewModel by viewModels()

    private lateinit var waterIntake: WaterIntake


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWaterIntakeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarWaterIntake.setupWithNavController(findNavController())
        binding.button.setOnClickListener(this)

        viewModel.waterIntakeLiveData.observe(viewLifecycleOwner) {
            waterIntake = it
            setupAdapter()
            //! Current business logic is in UI
            //! Need to be refactored later (using sealed class)
            if (it.quantity >= GameRules.GOALS_WATER_INTAKE) {
                binding.tvWaterSupportiveText.text = getString(R.string.water_intake_enough)
                binding.tvGlassLeft.text = getString(R.string.water_intake_glass_left, 0)
                if (!it.isCompleted) {
                    viewModel.updatePointsForGoals(it)
                    showToast(
                        getString(
                            R.string.got_point_template,
                            PointsManager.WATER_INTAKE_POINT
                        )
                    )
                }
            } else {
                val glassLeft = GameRules.GOALS_WATER_INTAKE - waterIntake.quantity
                binding.tvWaterSupportiveText.text = getString(R.string.water_intake_low)
                binding.tvGlassLeft.text =
                    getString(R.string.water_intake_glass_left, glassLeft)
            }
        }

    }


    override fun onClick(v: View?) {
        when (v) {
            binding.button -> {
                val adapter = WaterIntakeAdapter(waterIntake.quantity)
                binding.recyclerView.adapter = adapter

                viewModel.drinkOneGlass(waterIntake)
            }
        }
    }

    private fun setupAdapter() {
        val gridLayoutManager = GridLayoutManager(requireActivity(), 4)

        val waterIntakeAdapter = WaterIntakeAdapter(waterIntake.quantity)
        binding.recyclerView.apply {
            adapter = waterIntakeAdapter
            layoutManager = gridLayoutManager
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}