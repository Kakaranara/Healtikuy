package com.kocci.healtikuy.ui.main.leaderboards.running

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocci.healtikuy.core.constant.RunningType
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.databinding.FragmentRunningLeadTabBinding
import com.kocci.healtikuy.ui.main.leaderboards.LeadPointsAdapter
import com.kocci.healtikuy.util.extension.gone
import com.kocci.healtikuy.util.extension.showToast
import com.kocci.healtikuy.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RunningLeadTabFragment : Fragment() {
    private var _binding: FragmentRunningLeadTabBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RunningLeadVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rawType = arguments?.getString(RUNNING_TYPE)
        var runningType: RunningType? = null
        when (rawType) {
            "100" -> {
                runningType = RunningType.Running100M
            }

            "200" -> {
                runningType = RunningType.Running200M
            }

            "400" -> {
                runningType = RunningType.Running400M
            }

            else -> showToast("Error")
        }

        runningType?.let { type ->
            viewModel.getLeaderboardData(type).observe(viewLifecycleOwner) {
                when (it) {
                    is Async.Error -> {
                        binding.progressBar.gone()
                    }

                    Async.Loading -> {
                        binding.progressBar.visible()
                    }

                    is Async.Success -> {
                        binding.progressBar.gone()
                        val dataAdapter = viewModel.mapToLeaderPoints(it.data, type)
                        if (dataAdapter.isEmpty()) {
                            binding.tvNoItem.visible()
                        }
                        val mAdapter = LeadPointsAdapter(dataAdapter, requireActivity(), "Sec")
                        val mLayout = LinearLayoutManager(requireActivity())

                        binding.rvRunningLeaderboard.apply {
                            adapter = mAdapter
                            layoutManager = mLayout
                        }
                    }
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRunningLeadTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val RUNNING_TYPE = "running_type"
    }
}