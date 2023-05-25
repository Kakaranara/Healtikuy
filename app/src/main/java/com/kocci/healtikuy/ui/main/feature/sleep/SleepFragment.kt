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
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.domain.usecase.TimeIndicator
import com.kocci.healtikuy.core.util.helper.PointsManager
import com.kocci.healtikuy.core.util.helper.TipsManager
import com.kocci.healtikuy.databinding.FragmentSleepBinding
import com.kocci.healtikuy.ui.dialog.tips.withdialog.TipsBSheetDialogWithTabs
import com.kocci.healtikuy.ui.picker.TimePickerFragment
import com.kocci.healtikuy.util.extension.showToast
import com.kocci.healtikuy.util.helper.HistoryHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import java.util.Calendar


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
        setupToolbar()
        binding.btnChangeSleepTime.setOnClickListener(this)
        binding.cardSleepTips.btnMoreTips.setOnClickListener(this)

        viewModel.isTimeSet.observe(viewLifecycleOwner) { sleepIndicator ->
            when (sleepIndicator) {
                TimeIndicator.NotSet -> {
                    buttonClickGoesToTimer()
                    binding.tvSleepTime.text = getString(R.string.time_not_set)
                    binding.btnChangeSleepTime.visibility = View.GONE
                    //! This was actually bad practice.
                    //! Refactor later, use Frame Layout instead for this btn.
                    binding.btnSleepTime.isEnabled = true
                    binding.tvSleepDesc.text = getString(R.string.description_when_time_not_set)
                }

                is TimeIndicator.Set -> {
                    binding.tvSleepTime.text = viewModel.showFormattedTime(sleepIndicator.time)
                    binding.btnSleepTime.text = getString(R.string.sleep)
                    binding.btnChangeSleepTime.visibility = View.VISIBLE

                    viewModel.getDataModel.observe(viewLifecycleOwner) { sleep ->
                        if (sleep.isCompleted) {
                            binding.btnSleepTime.isEnabled = false
                            binding.tvSleepDesc.text =
                                getString(R.string.sleep_description_after_complete)
                        } else {
                            buttonClickGoesToCompleteMission(sleep)
                            viewModel.isTheTimeWithin1Hours(sleepIndicator.time)
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
                        }
                    }
                }
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbarSleep.apply {
            setupWithNavController(findNavController())
            setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.action_history -> {
                        runBlocking {
                            val list = viewModel.getAllData()
                            val historyList = HistoryHelper.orchestrateSleep(list)
                            val direction =
                                SleepFragmentDirections.actionGlobalHistoryFragment(historyList)
                            findNavController().navigate(direction)
                        }
                        true
                    }

                    R.id.action_clear_history -> {
                        viewModel.deleteHistory()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnChangeSleepTime -> {
                TimePickerFragment().show(childFragmentManager, "sleep")
            }

            binding.cardSleepTips.btnMoreTips -> {
                val tipList = TipsManager.generateSleepTips()
//                TipsDialogBSheet(tipList).show(childFragmentManager, "t")
                TipsBSheetDialogWithTabs(tipList).show(childFragmentManager, "sleep")
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
                showToast(getString(R.string.got_point_template, PointsManager.SLEEP_POINT))
            }
        }
    }

    override fun onTimeSet(tag: String?, hour: Int, minute: Int) {
        //? Handle the time dialog (when submitted)
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)

        viewModel.setSchedule(cal.timeInMillis)
        showToast("Alarm set!")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}