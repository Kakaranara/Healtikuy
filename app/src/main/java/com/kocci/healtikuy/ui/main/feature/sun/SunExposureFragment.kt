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
import com.kocci.healtikuy.core.util.helper.PointsManager
import com.kocci.healtikuy.core.util.helper.TipsManager
import com.kocci.healtikuy.databinding.FragmentSunExposureBinding
import com.kocci.healtikuy.ui.dialog.tips.linear.TipsDialogBSheet
import com.kocci.healtikuy.ui.picker.TimePickerFragment
import com.kocci.healtikuy.util.extension.showToast
import com.kocci.healtikuy.util.helper.HistoryHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
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
        binding.sunExposureTips.apply {
            tvBodyTips.text = getString(R.string.sun_tips_home)
            tvTitleTips.text = getString(R.string.tips)
            btnMoreTips.setOnClickListener {
                TipsDialogBSheet(TipsManager.generateSunExposureTips()).show(
                    childFragmentManager,
                    "sun_exposure"
                )
            }
        }


        viewModel.isTimeSet.observe(viewLifecycleOwner) { timeIndicator ->
            when (timeIndicator) {
                TimeIndicator.NotSet -> {
                    buttonClickGoesToTimer()
                    binding.tvSunfireTime.text = getString(R.string.time_not_set)
                    binding.btnChangeSunfireTime.visibility = View.GONE
                    binding.btnSunfireTime.isEnabled = true
                    binding.tvSunfireDesc.text = getString(R.string.description_when_time_not_set)
                }

                is TimeIndicator.Set -> {
                    binding.tvSunfireTime.text = viewModel.showFormattedTime(timeIndicator.time)
                    binding.btnSunfireTime.text = getString(R.string.sunbathe)
                    binding.btnChangeSunfireTime.visibility = View.VISIBLE

                    viewModel.getDataModel.observe(viewLifecycleOwner) { data ->
                        if (data.isCompleted) {
                            binding.btnSunfireTime.isEnabled = false
                            binding.tvSunfireDesc.text =
                                getString(R.string.sun_desc_after_complete)
                        } else {
                            buttonClickGoesToCompleteMission(data)
                            viewModel.isTheTimeWithin1Hours(timeIndicator.time)
                                .observe(viewLifecycleOwner) { isWithinOneHours ->
                                    if (!isWithinOneHours) {
                                        binding.btnSunfireTime.isEnabled = false
                                        binding.tvSunfireDesc.text =
                                            getString(R.string.sun_desc_not_in_time)
                                    } else {
                                        binding.btnSunfireTime.isEnabled = true
                                        binding.tvSunfireDesc.text =
                                            getString(R.string.sun_desc_before_press)
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
                showToast(getString(R.string.got_point_template, PointsManager.SUN_EXPOSURE_POINT))
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
        binding.toolbarSunfire.apply {
            setupWithNavController(findNavController())
            setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.action_history -> {
                        runBlocking {
                            val data = viewModel.getAllData()
                            val historyList = HistoryHelper.orchestrateSunExposure(data)

                            val direction =
                                SunExposureFragmentDirections.actionGlobalHistoryFragment(
                                    historyList
                                )
                            findNavController().navigate(direction)
                        }
                        true
                    }

                    R.id.action_clear_history -> {
                        viewModel.clearHistory()
                        true
                    }

                    else -> false
                }
            }
        }
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