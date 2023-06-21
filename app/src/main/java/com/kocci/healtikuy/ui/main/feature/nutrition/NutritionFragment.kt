package com.kocci.healtikuy.ui.main.feature.nutrition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.AvoidFeature
import com.kocci.healtikuy.core.util.helper.PointsManager
import com.kocci.healtikuy.core.util.helper.TipsManager
import com.kocci.healtikuy.databinding.FragmentNutritionBinding
import com.kocci.healtikuy.ui.dialog.tips.withdialog.TipsBSheetDialogWithTabs
import com.kocci.healtikuy.util.extension.showToast
import com.kocci.healtikuy.util.helper.HistoryHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class NutritionFragment : Fragment() {
    private var _binding: FragmentNutritionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NutritionViewModel by viewModels()

    private var dataObj: AvoidFeature? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        binding.nutritionTips.tvBodyTips.text = getString(R.string.nutrition_tips)
        binding.btnShowDialog.setOnClickListener {
            AddFoodDialog().show(childFragmentManager, "")
        }

        binding.nutritionTips.btnMoreTips.setOnClickListener {
            val tipList = TipsManager.generateNutritionTips()
            TipsBSheetDialogWithTabs(tipList).show(childFragmentManager, "nut")
        }

        viewModel.getUserShouldLimitData().observe(viewLifecycleOwner) {
            binding.checkSugar.isChecked = it.limitSugar
            binding.checkFat.isChecked = it.limitFat
            dataObj = it
        }

        viewModel.getData().observe(viewLifecycleOwner) {
            val mAdapter = NutritionAdapter(requireActivity(), it)
            val mLayoutManager = LinearLayoutManager(requireActivity())
            binding.rvNutrition.apply {
                adapter = mAdapter
                layoutManager = mLayoutManager
            }
        }

        val arrayBinding = arrayOf(binding.checkFat, binding.checkSugar)
        arrayBinding.forEach { binding ->
            handleCheckListener(binding)
        }

        handleClickListener(binding.checkFat) {
            viewModel.limitFat(it)
            showToast("You got ${PointsManager.AVOID_POINT} Points!")
        }

        handleClickListener(binding.checkSugar) {
            viewModel.limitSugar(it)
            showToast("You got ${PointsManager.AVOID_POINT} Points!")
        }

        if (!viewModel.isCheckable) {
            binding.apply {
                checkSugar.isEnabled = false
                checkFat.isEnabled = false
            }
        }
    }

    private fun handleCheckListener(checkView: CheckBox) {
        checkView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkView.isEnabled = false
            }
        }
    }

    private fun handleClickListener(checkView: CheckBox, operation: (data: AvoidFeature) -> Unit) {
        checkView.setOnClickListener {
            dataObj?.let {
                operation(it)
            } ?: kotlin.run {
                showToast("Please wait a moment!")
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbarNutrition.apply {
            setupWithNavController(findNavController())
            setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.action_history -> {
                        runBlocking {
                            val data = viewModel.getAllData()
                            val history = HistoryHelper.orchestrateNutrition(data)

                            val direction =
                                NutritionFragmentDirections.actionGlobalHistoryFragment(history)
                            findNavController().navigate(direction)
                            true
                        }
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
        _binding = FragmentNutritionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}