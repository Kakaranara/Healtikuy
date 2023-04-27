package com.kocci.healtikuy.ui.main.feature.exercise.cardio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocci.healtikuy.core.constant.CardioType
import com.kocci.healtikuy.core.domain.model.exercise.CardioStatus
import com.kocci.healtikuy.databinding.FragmentCardioListBinding


class CardioListFragment : Fragment() {
    private var _binding: FragmentCardioListBinding? = null
    private val binding get() = _binding!!
    private fun generateCardioList(): List<CardioStatus> {
        return listOf(
            CardioStatus(CardioType.JOGGING, false),
            CardioStatus(CardioType.RUNNING, false),
            CardioStatus(CardioType.STATIC_BIKE, false),
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
//                        JOGGING -> {
//
//                        }
                        CardioType.JOGGING -> {
                            val direction =
                                CardioListFragmentDirections.actionCardioListFragmentToJoggingFragment()
                            findNavController().navigate(direction)
                        }
                        CardioType.RUNNING -> {
                            val direction =
                                CardioListFragmentDirections.actionCardioListFragmentToRunningFragment()
                            findNavController().navigate(direction)
                        }
                        CardioType.STATIC_BIKE -> {
                            val direction =
                                CardioListFragmentDirections.actionCardioListFragmentToStaticBikeFragment()
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