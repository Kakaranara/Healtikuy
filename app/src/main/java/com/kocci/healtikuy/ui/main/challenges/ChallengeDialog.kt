package com.kocci.healtikuy.ui.main.challenges

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.Challenge
import com.kocci.healtikuy.databinding.DialogChallengesBinding
import com.kocci.healtikuy.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeDialog(context: Context) : DialogFragment() {

    private var _binding: DialogChallengesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChallengeViewModel by viewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireActivity())
        _binding = DialogChallengesBinding.inflate(requireActivity().layoutInflater)

        builder.setView(binding.root)
        return builder.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.progress.observe(viewLifecycleOwner) {
            when (it) {
                is Async.Error -> {
                    showToast("Error ${it.msg}")
                }

                Async.Loading -> {
                    showToast("Loading..")
                }

                is Async.Success -> {
                    setupAdapter(it.data)
                    showToast("Success")
                }
            }
        }
    }

    private fun setupAdapter(list: List<Challenge>) {
        val mAdapter = ChallengeAdapter(list)
        val mLayoutManager = LinearLayoutManager(requireActivity())
        binding.rvChallenges.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun generateList(): List<Challenge> {
        return List(20) {
            Challenge("Reach point > 5", 40, false, "points")
        }
    }
}