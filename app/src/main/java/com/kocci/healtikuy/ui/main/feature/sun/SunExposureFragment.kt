package com.kocci.healtikuy.ui.main.feature.sun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.SunExposure
import com.kocci.healtikuy.core.domain.usecase.TimeIndicator
import com.kocci.healtikuy.databinding.FragmentSunExposureBinding
import com.kocci.healtikuy.ui.picker.TimePickerFragment
import com.kocci.healtikuy.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar


@AndroidEntryPoint
class SunExposureFragment : Fragment(), TimePickerFragment.TimePickerListener,
    View.OnClickListener {
    private var _binding: FragmentSunExposureBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SunExposureViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        binding.btnChangeSunfireTime.setOnClickListener(this)

        viewModel.isTimeSet.observe(viewLifecycleOwner) { timeIndicator ->
            when (timeIndicator) {
                TimeIndicator.NotSet -> {
                    buttonClickGoesToTimer()
                    binding.tvSunfireTime.text = getString(R.string.time_not_set)
                    binding.btnChangeSunfireTime.visibility = View.GONE
                    binding.tvSunfireDesc.text = getString(R.string.description_when_time_not_set)
                }

                is TimeIndicator.Set -> {
                    binding.tvSunfireTime.text = viewModel.showFormattedTime(timeIndicator.time)
                    binding.btnSunfireTime.text = "Sunbathe"
                    binding.btnChangeSunfireTime.visibility = View.VISIBLE

                    viewModel.getDataModel.observe(viewLifecycleOwner) { data ->
                        showToast(data.toString())
                        if (data.isCompleted) {
                            binding.btnSunfireTime.isEnabled = false
                            binding.tvSunfireDesc.text =
                                getString(R.string.sleep_description_after_complete)
                        } else {
                            buttonClickGoesToCompleteMission(data)
                            viewModel.isTheTimeWithin1Hours(timeIndicator.time)
                                .observe(viewLifecycleOwner) { isWithinOneHours ->
                                    if (!isWithinOneHours) {
                                        binding.btnSunfireTime.isEnabled = false
                                        binding.tvSunfireDesc.text =
                                            getString(R.string.sleep_description_not_in_time)
                                        TODO(" change sleep to sunfire desc")
                                    } else {
                                        binding.btnSunfireTime.isEnabled = true
                                        binding.tvSunfireDesc.text =
                                            getString(R.string.sleep_description_before_sleep)
                                    }
                                }
                        }
                    }
                }
            }
        }
    }

    override fun onTimeSet(tag: String?, hour: Int, minute: Int) {
        //? Handle the time dialog (when submitted)
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)

        viewModel.setSchedule(cal.timeInMillis)
        showToast("$hour : $minute")
    }

    private fun buttonClickGoesToTimer() {
        binding.btnSunfireTime.apply {
            setOnClickListener(null)
            text = getString(R.string.set_time)
            setOnClickListener {
                TimePickerFragment().show(childFragmentManager, "sleep")
            }
        }
    }

    private fun buttonClickGoesToCompleteMission(data: SunExposure) {
        binding.btnSunfireTime.apply {
            setOnClickListener(null)
            setOnClickListener {
                viewModel.completeMission(data)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnChangeSunfireTime -> {
                TimePickerFragment().show(childFragmentManager, "sleep")
            }
        }
    }


    private fun setupToolbar() {
        binding.toolbarSunfire.setupWithNavController(findNavController())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSunExposureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}