package com.kocci.healtikuy.ui.main.feature.water

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kocci.healtikuy.databinding.FragmentWaterIntakeBinding

class WaterIntakeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentWaterIntakeBinding? = null
    private val binding get() = _binding!!
    var itemCount = 1

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

        binding.button.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v) {
            binding.button -> {
                itemCount++
                val adapter = WaterIntakeAdapter(itemCount)
                binding.recyclerView.adapter = adapter
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