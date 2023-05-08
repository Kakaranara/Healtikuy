package com.kocci.healtikuy.ui.main.feature.nutrition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocci.healtikuy.databinding.FragmentNutritionBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NutritionFragment : Fragment() {
    private var _binding: FragmentNutritionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NutritionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
//        binding.btnNutritionSubmit.setOnClickListener {
//            val foodName = binding.etNutritionFood.text.toString()
//            viewModel.addFood(Nutrition(foodName))
//        }
        binding.btnShowDialog.setOnClickListener {
            AddFoodDialog().show(childFragmentManager, "")
        }

        viewModel.getData().observe(viewLifecycleOwner) {
            val mAdapter = NutritionAdapter(it)
            val mLayoutManager = LinearLayoutManager(requireActivity())
            binding.rvNutrition.apply {
                adapter = mAdapter
                layoutManager = mLayoutManager
            }
        }

    }

    private fun setupToolbar() {
        binding.toolbarNutrition.apply {
            setupWithNavController(findNavController())
            setOnMenuItemClickListener { menu ->
                when(menu.itemId){
                    //TODO : func hist
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