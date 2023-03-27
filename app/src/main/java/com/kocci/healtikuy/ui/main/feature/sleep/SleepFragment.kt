package com.kocci.healtikuy.ui.main.feature.sleep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.databinding.FragmentSleepBinding
import com.kocci.healtikuy.ui.picker.TimePickerFragment
import com.kocci.healtikuy.util.extension.showToast


class SleepFragment : Fragment(), View.OnClickListener, TimePickerFragment.TimePickerListener {
    private var _binding: FragmentSleepBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSleepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarSleep.setupWithNavController(findNavController())

        binding.btnSleepTime.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnSleepTime -> {
                TimePickerFragment().show(childFragmentManager, "fsf")
            }
        }
    }

    override fun onTimeSet(tag: String?, hour: Int, minute: Int) {
        showToast("$hour : $minute")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}