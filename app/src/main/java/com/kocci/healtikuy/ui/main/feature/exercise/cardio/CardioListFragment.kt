package com.kocci.healtikuy.ui.main.feature.exercise.cardio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocci.healtikuy.core.domain.usecase.CardioStatus
import com.kocci.healtikuy.databinding.FragmentCardioListBinding


class CardioListFragment : Fragment() {
    private var _binding: FragmentCardioListBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val CARDIO = "cardio"
        const val JOGGING = "Jogging"
        const val STATIC_BIKE = "Static Bike"
    }

    private fun generateCardioList(): List<CardioStatus> {
        return listOf(
            CardioStatus(JOGGING),
            CardioStatus(STATIC_BIKE),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarCardio.setupWithNavController(findNavController())

        setupAdapter()
    }

    private fun setupAdapter() {
        val listOfCardio = generateCardioList()
        val mAdapter = CardioListAdapter(listOfCardio).apply {
            listener = object : CardioListAdapter.ClickListener {
                override fun OnCardioClick(data: CardioStatus) {
                    when (data.name) {
                        JOGGING -> {
                            val direction =
                                CardioListFragmentDirections.actionCardioListFragmentToJoggingFragment()
                            findNavController().navigate(direction)
                        }
                    }
                }
            }
        }
        val mLayoutManager = LinearLayoutManager(requireActivity())

        binding.rvCardioList.adapter = mAdapter
        binding.rvCardioList.layoutManager = mLayoutManager
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardioListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}