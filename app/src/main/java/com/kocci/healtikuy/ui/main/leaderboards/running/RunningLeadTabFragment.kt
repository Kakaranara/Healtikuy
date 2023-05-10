package com.kocci.healtikuy.ui.main.leaderboards.running

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kocci.healtikuy.R
import com.kocci.healtikuy.databinding.FragmentRunningLeadTabBinding


class RunningLeadTabFragment : Fragment() {
    private var _binding : FragmentRunningLeadTabBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRunningLeadTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val RUNNING_TYPE = "running_type"
    }
}