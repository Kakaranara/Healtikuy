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


@AndroidEntryPoint
class AvoidSomethingFragment : Fragment(){
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
            tvTitleTips.text = "Information"
            tvBodyTips.text = getString(R.string.tips_avoid)
            btnMoreTips.gone()
        }


        viewModel.data.observe(viewLifecycleOwner) {
            binding.checkAlcohol.isChecked = it.alcohol
            binding.checkSmoking.isChecked = it.smoke
            dataObj = it
        }

        val arrayBinding = arrayOf(binding.checkAlcohol, binding.checkSmoking)
        arrayBinding.forEach { binding ->
            handleCheckListener(binding)
        }

        handleClickListener(binding.checkSmoking){
            viewModel.checkSmoke(it)
        }

        handleClickListener(binding.checkAlcohol){
            viewModel.checkAlcohol(it)
        }
    }

    private fun handleCheckListener(checkView: CheckBox) {
        checkView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkView.isEnabled = false
            }
        }
    }

    private fun handleClickListener(checkView: CheckBox, operation : (data: AvoidFeature) -> Unit) {
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