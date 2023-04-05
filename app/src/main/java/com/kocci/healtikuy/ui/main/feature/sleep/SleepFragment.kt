package com.kocci.healtikuy.ui.main.feature.sleep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.domain.usecase.SleepIndicator
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
                    buttonClickGoesToTimer()
                    binding.tvSleepTime.text = getString(R.string.time_not_set)
                    binding.btnChangeSleepTime.visibility = View.GONE
                    binding.tvSleepDesc.text = getString(R.string.description_when_time_not_set)
                }
                is SleepIndicator.Set -> {
                    showToast(sleepIndicator.toString())
                    binding.tvSleepTime.text = viewModel.showFormattedTime(sleepIndicator.data)
                    binding.btnSleepTime.text = getString(R.string.sleep)
                    binding.btnChangeSleepTime.visibility = View.VISIBLE

                    viewModel.getDataModel.observe(viewLifecycleOwner) { sleep ->
                        if (sleep.isCompleted) {
                            //! need to be updated
                            //! Doesnt seems to be good
                            val successColor = ContextCompat.getColor(
                                requireActivity(),
                                R.color.green_success
                            )
                            binding.btnSleepTime.isEnabled = false
                            binding.btnSleepTime.setBackgroundColor(successColor)
                            binding.tvSleepDesc.text =
                                getString(R.string.sleep_description_after_complete)
                        } else {
                            viewModel.isTheTimeWithin1Hours(sleepIndicator.data)
                                .observe(viewLifecycleOwner) { isWithinOneHours ->
                                    if (!isWithinOneHours) {
                                        binding.btnSleepTime.isEnabled = false
                                        binding.tvSleepDesc.text =
                                            getString(R.string.sleep_description_not_in_time)
                                    } else {
                                        binding.btnSleepTime.isEnabled = true
                                        binding.tvSleepDesc.text =
                                            getString(R.string.sleep_description_before_sleep)
                                    }
                                }
                            buttonClickGoesToCompleteMission(sleep)
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

    private fun buttonClickGoesToTimer() {
        binding.btnSleepTime.apply {
            setOnClickListener(null)
            text = getString(R.string.set_time)
            setOnClickListener {
                TimePickerFragment().show(childFragmentManager, "sleep")
            }
        }
    }

    private fun buttonClickGoesToCompleteMission(sleepData: Sleep) {
        binding.btnSleepTime.apply {
            setOnClickListener(null)
            setOnClickListener {
                viewModel.completeMission(sleepData)
            }
        }
    }

    override fun onTimeSet(tag: String?, hour: Int, minute: Int) {
        //? Handle the time dialog (when submitted)
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)

        viewModel.setTime(cal.timeInMillis)
        viewModel.setSchedule(requireActivity().applicationContext, Sleep(), cal.timeInMillis)
        showToast("$hour : $minute")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}