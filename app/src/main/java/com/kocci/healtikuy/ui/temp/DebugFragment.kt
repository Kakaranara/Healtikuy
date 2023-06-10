package com.kocci.healtikuy.ui.temp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.databinding.FragmentDebugBinding


class DebugFragment : Fragment() {
    private var _binding: FragmentDebugBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDebugBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarDebug.setupWithNavController(findNavController())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}