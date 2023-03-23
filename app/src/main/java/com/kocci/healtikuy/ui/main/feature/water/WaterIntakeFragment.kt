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
import com.kocci.healtikuy.core.domain.model.WaterIntake
import com.kocci.healtikuy.databinding.FragmentWaterIntakeBinding
import com.kocci.healtikuy.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WaterIntakeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentWaterIntakeBinding? = null
    private val binding get() = _binding!!
    var itemCount = 1

    var waterIntake: WaterIntake? = null
    val viewModel: WaterIntakeViewModel by viewModels()

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
        setupAdapter()

        viewModel.listenMe.observe(viewLifecycleOwner) {
            waterIntake = it
            showToast(it.toString())
        }

        binding.button.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v) {
            binding.button -> {
                itemCount++
                val adapter = WaterIntakeAdapter(itemCount)
                binding.recyclerView.adapter = adapter

                viewModel.updateData(waterIntake!!)
            }
        }
    }

    private fun setupAdapter() {
        val gridLayoutManager = GridLayoutManager(requireActivity(), 4)

        val waterIntakeAdapter = WaterIntakeAdapter(itemCount)
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