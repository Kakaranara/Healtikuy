package com.kocci.healtikuy.ui.main.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kocci.healtikuy.databinding.FragmentSunExposureBinding
import com.kocci.healtikuy.databinding.FragmentWaterIntakeBinding

class WaterIntakeFragment : Fragment() {
    private var _binding: FragmentWaterIntakeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWaterIntakeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}