package com.kocci.healtikuy.ui.main.feature.exercise.cardio.jogging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.exercise.Jogging
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.scheduler.ExerciseTimeIndicator
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.databinding.FragmentJoggingBinding
import com.kocci.healtikuy.ui.picker.TimePickerFragment
import com.kocci.healtikuy.util.extension.gone
import com.kocci.healtikuy.util.extension.showToast
import com.kocci.healtikuy.util.extension.visible
import com.kocci.healtikuy.util.helper.HistoryHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import java.util.*

@AndroidEntryPoint
class JoggingFragment : Fragment(), View.OnClickListener, TimePickerFragment.TimePickerListener {
    private var _binding: FragmentJoggingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JoggingViewModel by viewModels()

    private var joggingValue: Jogging? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        binding.btnExerciseTimeSubmit.setOnClickListener(this)
        binding.btnExerciseTimeEdit.setOnClickListener(this)
        binding.btnExerciseSetTime.setOnClickListener(this)
        binding.btnJoggingSubmit.setOnClickListener(this)

        viewModel.getSchedule().observe(viewLifecycleOwner) {
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

        viewModel.getProgressData().observe(viewLifecycleOwner) {
            if (it.isCompleted) {
                binding.apply {
                    tvJoggingDuration.visible()
                    tvJoggingDuration.text = it.duration.toString()
                    etJoggingDuration.gone()
                    tvJoggingMileage.visible()
                    tvJoggingMileage.text = it.distance.toString()
                    etJoggingMileage.gone()
                    btnJoggingSubmit.isEnabled = false
                }
            } else {
                binding.apply {
                    tvJoggingDuration.gone()
                    tvJoggingMileage.gone()
                    etJoggingDuration.visible()
                    etJoggingMileage.visible()
                    joggingValue = it
                }
            }
        }
    }

    private fun setupToolbar() {
        binding.apply {
            toolbarJogging.setupWithNavController(findNavController())
            toolbarJogging.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_history -> {
                        //TODO : ORCHESTRATE HISTORY LIST HERE
                        runBlocking {
                            val joggingList = viewModel.getAllData()
                            val historyList = HistoryHelper.orchestrateJogging(joggingList)
                            val direction =
                                JoggingFragmentDirections.actionGlobalHistoryFragment(historyList)

                            findNavController().navigate(direction)
                        }

                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
        binding.toolbarJogging.setupWithNavController(findNavController())
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnExerciseSetTime -> {
                val picker = TimePickerFragment()
                picker.show(childFragmentManager, "jogging")
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
            binding.btnJoggingSubmit -> {
                val duration = binding.etJoggingDuration.text.toString().toInt()
                val mileage = binding.etJoggingMileage.text.toString().toInt()
                joggingValue?.let {
                    it.duration = duration
                    it.distance = mileage
                    viewModel.updateData(it)
                } ?: run {
                    showToast("Make sure!")
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoggingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}