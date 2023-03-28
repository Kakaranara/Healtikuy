package com.kocci.healtikuy.ui.main.feature.sleep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.usecase.SleepIndicator
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.databinding.FragmentSleepBinding
import com.kocci.healtikuy.ui.picker.TimePickerFragment
import com.kocci.healtikuy.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class SleepFragment : Fragment(), View.OnClickListener, TimePickerFragment.TimePickerListener {
    private var _binding: FragmentSleepBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SleepViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSleepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarSleep.setupWithNavController(findNavController())
        binding.btnChangeSleepTime.setOnClickListener(this)

        viewModel.isTimeSet.observe(viewLifecycleOwner) { sleepIndicator ->
            when (sleepIndicator) {
                SleepIndicator.NotSet -> {
                    showToast(sleepIndicator.toString())
                    binding.btnSleepTime.apply {
                        setOnClickListener(null)
                        text = getString(R.string.set_time)
                        setOnClickListener {
                            TimePickerFragment().show(childFragmentManager, "sleep")
                        }
                    }
                    binding.tvSleepTime.text = getString(R.string.time_not_set)
                    binding.btnChangeSleepTime.visibility = View.GONE
                }
                is SleepIndicator.Set -> {
                    showToast(sleepIndicator.toString())
                    binding.tvSleepTime.text = DateHelper.showHoursAndMinutes(sleepIndicator.data)
                    binding.btnSleepTime.text = getString(R.string.sleep)
                    binding.btnChangeSleepTime.visibility = View.VISIBLE

                    viewModel.getDataModel.observe(viewLifecycleOwner) { sleep ->
                        if (sleep.isCompleted) {
                            binding.btnSleepTime.isEnabled = false
                        } else {
                            binding.btnSleepTime.apply {
                                setOnClickListener(null)
                                setOnClickListener {
                                    showToast("hai aku")
                                    viewModel.completeMission(sleep)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnChangeSleepTime -> {
                TimePickerFragment().show(childFragmentManager, "sleep")
            }
        }
    }

    override fun onTimeSet(tag: String?, hour: Int, minute: Int) {
        //? Handle the time dialog (when submitted)
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)

        viewModel.setTime(cal.timeInMillis)
        showToast("$hour : $minute")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}