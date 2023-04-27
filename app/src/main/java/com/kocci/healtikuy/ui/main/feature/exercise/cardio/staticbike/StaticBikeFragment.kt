package com.kocci.healtikuy.ui.main.feature.exercise.cardio.staticbike

import android.os.Bundle
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
import com.kocci.healtikuy.util.helper.HistoryHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import java.util.Calendar


@AndroidEntryPoint
class StaticBikeFragment : Fragment(), View.OnClickListener, TimePickerFragment.TimePickerListener {
    private var _binding: FragmentStaticBikeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StaticBikeViewModel by viewModels()

    private var staticBikeValue: StaticBike? = null

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

        viewModel.progress.observe(viewLifecycleOwner) { it ->
            if (it.isCompleted) {
                binding.apply {
                    tvStaticBikeInterval.visible()
                    tvStaticBikeRest.visible()
                    tvStaticBikeSet.visible()
                    etStaticBikeInterval.gone()
                    etStaticBikeRest.gone()
                    etStaticBikeSet.gone()
                    tvStaticBikeInterval.text = it.interval.toString()
                    tvStaticBikeRest.text = it.restTime.toString()
                    tvStaticBikeSet.text = it.set.toString()
                    btnStaticBikeSubmit.isEnabled = false

                }
            } else {
                binding.apply {
                    tvStaticBikeInterval.gone()
                    tvStaticBikeRest.gone()
                    tvStaticBikeSet.gone()
                    etStaticBikeInterval.visible()
                    etStaticBikeRest.visible()
                    etStaticBikeSet.visible()
                    staticBikeValue = it
                }
            }
        }
    }

    private fun setupButtonBinding() {
        binding.btnExerciseSetTime.setOnClickListener(this)
        binding.btnExerciseTimeEdit.setOnClickListener(this)
        binding.btnExerciseTimeSubmit.setOnClickListener(this)
        binding.btnStaticBikeSubmit.setOnClickListener(this)
    }

    private fun setupToolbar() {
        binding.toolbarStaticBike.apply {
            setupWithNavController(findNavController())
            setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.action_history -> {
                        runBlocking {
                            val data = viewModel.getAllData()
                            val history = HistoryHelper.orchestrateStaticBike(data)
                            val direction =
                                StaticBikeFragmentDirections.actionGlobalHistoryFragment(history)
                            findNavController().navigate(direction)
                        }
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

            binding.btnStaticBikeSubmit -> {
                val set = binding.etStaticBikeSet.text.toString().toInt()
                val interval = binding.etStaticBikeInterval.text.toString().toInt()
                val restTime = binding.etStaticBikeRest.text.toString().toInt()
                staticBikeValue?.let { staticBike ->
                    staticBike.set = set
                    staticBike.interval = interval
                    staticBike.restTime = restTime

                    viewModel.submitData(staticBike)
                } ?: kotlin.run {
                    showToast("NO DATA!")
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
        _binding = FragmentStaticBikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}