package com.kocci.healtikuy.ui.main.feature.exercise.cardio.running

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.exercise.Running
import com.kocci.healtikuy.core.domain.usecase.exercise.scheduler.ExerciseTimeIndicator
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.databinding.FragmentRunningBinding
import com.kocci.healtikuy.ui.picker.TimePickerFragment
import com.kocci.healtikuy.util.extension.gone
import com.kocci.healtikuy.util.extension.showToast
import com.kocci.healtikuy.util.extension.visible
import com.kocci.healtikuy.util.helper.HistoryHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import java.util.Calendar


@AndroidEntryPoint
class RunningFragment : Fragment(), View.OnClickListener, TimePickerFragment.TimePickerListener {
    private var _binding: FragmentRunningBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RunningViewModel by viewModels()
    private var runningValue: Running? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupButtonBinding()

        viewModel.schedule.observe(viewLifecycleOwner) {
            when (it) {
                is ExerciseTimeIndicator.Set -> {
                    binding.apply {
                        spacer.visible()
                        tvInterval.visible()
                        etInterval.gone()
                        btnExerciseSetTime.gone()
                        btnExerciseTimeEdit.visible()
                        tvInterval.text = it.interval.toString()
                        tvExerciseTime.text = DateHelper.showHoursAndMinutes(it.time)
                    }
                }

                ExerciseTimeIndicator.NotSet -> {
                    binding.apply {
                        spacer.gone()
                        tvInterval.gone()
                        etInterval.visible()
                        btnExerciseSetTime.visible()
                        btnExerciseTimeSubmit.visible()
                        btnExerciseTimeEdit.gone()
                    }
                }
            }
        }

        viewModel.progress.observe(viewLifecycleOwner) {
            if (it.isCompleted) {
                binding.apply {
                    tvRunningDuration.visible()
                    tvRunningDuration.text = it.duration.toString()
                    etRunningDuration.gone()
                    tvRunningMileage.visible()
                    tvRunningMileage.text = it.distance.toString()
                    actvRunningMileage.gone()
                    ilRunningMilleage.gone()
                    btnRunningSubmit.isEnabled = false
                }
            } else {
                binding.apply {
                    tvRunningDuration.gone()
                    tvRunningMileage.gone()
                    etRunningDuration.visible()
                    actvRunningMileage.visible()
                    ilRunningMilleage.visible()
                    runningValue = it
                }
            }
        }

    }

    private fun setupButtonBinding() {
        binding.btnExerciseTimeSubmit.setOnClickListener(this)
        binding.btnExerciseTimeEdit.setOnClickListener(this)
        binding.btnExerciseSetTime.setOnClickListener(this)
        binding.btnRunningSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnExerciseSetTime -> {
                val picker = TimePickerFragment()
                picker.show(childFragmentManager, "Running")
            }

            binding.btnExerciseTimeSubmit -> {
                val timeInString = binding.tvExerciseTime.text.toString()
                val interval = binding.etInterval.text.toString().toInt()
                viewModel.setExerciseSchedule(timeInString, interval)
                showToast("Alarm set!")
            }

            binding.btnExerciseTimeEdit -> {
                viewModel.editSchedule()
            }

            binding.btnRunningSubmit -> {
                val duration = binding.etRunningDuration.text.toString().toInt()
                val mileage = binding.actvRunningMileage.text.toString().toInt()
                try {
                    runningValue?.let {
                        it.duration = duration
                        it.distance = mileage
                        viewModel.updateData(it)
                    } ?: kotlin.run {
                        showToast("Data is null!")
                    }
                } catch (e: Exception) {
                    Log.e("Running fragment", "onClick: ${e.message}")
                    showToast("Failed to execute!")
                }
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbarRunning.apply {
            setupWithNavController(findNavController())
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_history -> {
                        //TODO : ORCHESTRATE HERE and NAVIGATE
                        runBlocking {
                            val data = viewModel.getAllData()
                            val orchestratedData = HistoryHelper.orchestrateRunning(data)
                            val direction =
                                RunningFragmentDirections.actionGlobalHistoryFragment(
                                    orchestratedData
                                )

                            findNavController().navigate(direction)
                        }
                        true
                    }

                    else -> false
                }
            }
        }
    }

    override fun onTimeSet(tag: String?, hour: Int, minute: Int) {
        val cal = Calendar.getInstance()
        cal.apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        val formattedTime = viewModel.showFormattedTime(cal.timeInMillis)
        binding.tvExerciseTime.text = formattedTime
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}