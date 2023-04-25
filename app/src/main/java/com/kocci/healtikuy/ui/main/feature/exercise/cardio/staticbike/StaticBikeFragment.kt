package com.kocci.healtikuy.ui.main.feature.exercise.cardio.staticbike

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
import com.kocci.healtikuy.core.domain.model.exercise.StaticBike
import com.kocci.healtikuy.core.domain.usecase.exercise.scheduler.ExerciseTimeIndicator
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.databinding.FragmentStaticBikeBinding
import com.kocci.healtikuy.ui.picker.TimePickerFragment
import com.kocci.healtikuy.util.extension.gone
import com.kocci.healtikuy.util.extension.showToast
import com.kocci.healtikuy.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar


@AndroidEntryPoint
class StaticBikeFragment : Fragment(), View.OnClickListener, TimePickerFragment.TimePickerListener {
    private var _binding: FragmentStaticBikeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StaticBikeViewModel by viewModels()

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
    }

    private fun setupButtonBinding() {
        binding.btnExerciseSetTime.setOnClickListener(this)
        binding.btnExerciseTimeEdit.setOnClickListener(this)
        binding.btnExerciseTimeSubmit.setOnClickListener(this)
    }

    private fun setupToolbar() {
        binding.toolbarStaticBike.apply {
            setupWithNavController(findNavController())
            setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.action_history -> {
                        //TODOS : ORCHESTRATE
                        true
                    }

                    else -> false
                }
            }
        }

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
//
//            binding.btnRunningSubmit -> {
//                val duration = binding.etRunningDuration.text.toString().toInt()
//                val mileage = binding.actvRunningMileage.text.toString().toInt()
//                try {
//                    runningValue?.let {
//                        it.duration = duration
//                        it.distance = mileage
//                        viewModel.updateData(it)
//                    } ?: kotlin.run {
//                        showToast("Data is null!")
//                    }
//                } catch (e: Exception) {
//                    Log.e("Running fragment", "onClick: ${e.message}")
//                    showToast("Failed to execute!")
//                }
//            }
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
        _binding = FragmentStaticBikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}