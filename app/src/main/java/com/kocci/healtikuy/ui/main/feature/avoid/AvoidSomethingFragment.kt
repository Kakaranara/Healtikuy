package com.kocci.healtikuy.ui.main.feature.avoid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.AvoidFeature
import com.kocci.healtikuy.databinding.FragmentAvoidSometingBinding
import com.kocci.healtikuy.util.extension.gone
import com.kocci.healtikuy.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class AvoidSomethingFragment : Fragment() {
    /**
     * Please change "AvoidFeature" to something else..
     */
    private var _binding: FragmentAvoidSometingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AvoidSmthViewModel by viewModels()
    private var dataObj: AvoidFeature? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarAvoid.setupWithNavController(findNavController())
        binding.tipsAvoid.apply {
            tvTitleTips.text = getString(R.string.information)
            tvBodyTips.text = getString(R.string.tips_avoid)
            btnMoreTips.gone()
        }


        viewModel.data.observe(viewLifecycleOwner) {
            binding.checkAlcohol.isChecked = it.alcohol
            binding.checkSmoking.isChecked = it.smoke
            dataObj = it
        }

        viewModel.neverDoneItLiveData.observe(viewLifecycleOwner) { never ->
            if (never) {
                binding.checkAll.isChecked = true
                runBlocking {
                    val data = viewModel.getAwaitedData()
                    if (!data.isTodayAllChecked) {
                        viewModel.checkAll(data)
                        showToast("You got X points! ")
                    }
                }
            } else {
                binding.checkAll.isChecked = false
            }
        }

        val arrayBinding = arrayOf(binding.checkAlcohol, binding.checkSmoking)
        arrayBinding.forEach { binding ->
            handleCheckListener(binding)
        }

        handleClickListener(binding.checkSmoking) {
            viewModel.checkSmoke(it)
            showToast("You got X Points!")
        }

        handleClickListener(binding.checkAlcohol) {
            viewModel.checkAlcohol(it)
            showToast("You got X Points!")
        }

        binding.checkAll.setOnCheckedChangeListener { _, isChecked ->
            viewModel.changeNeverDoneState(isChecked)
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAvoidSometingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}