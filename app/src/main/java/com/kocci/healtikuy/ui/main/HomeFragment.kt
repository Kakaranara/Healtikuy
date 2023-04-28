package com.kocci.healtikuy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.constant.GameRules
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.usecase.HealthyStatusIndicator
import com.kocci.healtikuy.databinding.FragmentHomeBinding
import com.kocci.healtikuy.ui.dialog.ChallengeDialog
import com.kocci.healtikuy.util.extension.showToast
import com.kocci.healtikuy.util.helper.DrawableHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAppBarWithMenuDrawer()
        bindClickListener()

        runBlocking {
            if (!viewModel.isUserLogin()) {
                val goToLogin = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                findNavController().navigate(goToLogin)
            }
        }

        viewModel.getUserData().observe(viewLifecycleOwner) {
            binding.imgAvatar.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    DrawableHelper.getIdentifier(requireActivity(), it.avatar)
                )
            )
            binding.apply {
                tvName.text = it.username.replaceFirstChar(Char::titlecase)
                tvCoin.text = it.coin.toString()
            }
        }

        //? observing points and statusbar
        viewModel.healthyStatus.observe(viewLifecycleOwner) { healthyStatusIndicator ->
            val progressStatus = viewModel.calculateStatusPercentage(healthyStatusIndicator.point)
            val pointView = getString(
                R.string.status_points,
                healthyStatusIndicator.point,
                GameRules.MAX_STATUS_POINTS
            )
            binding.apply {
                statusIndicator.setProgressCompat(progressStatus, true)
                binding.tvPoint.text = pointView
            }

//            showToast(healthyStatusIndicator.toString())

            when (healthyStatusIndicator) {
                is HealthyStatusIndicator.Completed -> {
                    binding.desc.text = getString(R.string.status_completed)
//                    showToast("completed : ${healthyStatusIndicator.point}")
                }

                is HealthyStatusIndicator.NearlyComplete -> {
                    binding.desc.text = getString(R.string.status_near_complete)
//                    showToast("nearly complete ${healthyStatusIndicator.point}")
                }

                is HealthyStatusIndicator.MidComplete -> {
                    binding.desc.text = getString(R.string.status_mid)
//                    showToast("mid complete ${healthyStatusIndicator.point}")
                }

                is HealthyStatusIndicator.Low -> {
                    binding.desc.text = getString(R.string.status_low)
//                    showToast("low complete ${healthyStatusIndicator.point}")
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnWaterIntake -> {
                val directions = HomeFragmentDirections.actionHomeFragmentToWaterIntakeFragment()
                findNavController().navigate(directions)
            }

            binding.btnSleep -> {
                val directions = HomeFragmentDirections.actionHomeFragmentToSleepFragment()
                findNavController().navigate(directions)
            }

            binding.btnExercise -> {
                val directions = HomeFragmentDirections.actionHomeFragmentToExerciseFragment()
                findNavController().navigate(directions)
            }

            binding.btnNutrition -> {
                val directions = HomeFragmentDirections.actionHomeFragmentToNutritionFragment()
                findNavController().navigate(directions)
            }

            binding.btnSunExposure -> {
                val directions = HomeFragmentDirections.actionHomeFragmentToSunExposureFragment()
                findNavController().navigate(directions)
            }

            binding.btnChallenges -> {
                ChallengeDialog(requireActivity()).show(parentFragmentManager, "me")
            }
        }
    }

    private fun bindClickListener() {
        binding.btnExercise.setOnClickListener(this)
        binding.btnNutrition.setOnClickListener(this)
        binding.btnSleep.setOnClickListener(this)
        binding.btnSunExposure.setOnClickListener(this)
        binding.btnWaterIntake.setOnClickListener(this)
        binding.btnChallenges.setOnClickListener(this)
    }

    private fun setupAppBarWithMenuDrawer() {
        val navController = findNavController()
        val drawerLayout = activity?.findViewById<DrawerLayout>(R.id.drawer_layout)
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        binding.toolbar.apply {
            setupWithNavController(navController, appBarConfiguration)
            setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.action_sync_local -> {
                        MaterialAlertDialogBuilder(requireActivity())
                            .setTitle(resources.getString(R.string.are_you_sure))
                            .setMessage(resources.getString(R.string.local_sync_is_sure))
                            .setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                                viewModel.localSync().observe(viewLifecycleOwner) {
                                    when (it) {
                                        is Async.Error -> {
                                            showToast("Error : ${it.msg}")
                                        }

                                        Async.Loading -> {
                                            showToast("Loading..")
                                        }

                                        is Async.Success -> {
                                            showToast("Success ")
                                        }
                                    }
                                }
                            }
                            .setNegativeButton(resources.getString(R.string.no)) { _, _ ->

                            }.show()
                        true
                    }

                    R.id.action_sync_remote -> {
                        MaterialAlertDialogBuilder(requireActivity())
                            .setTitle(resources.getString(R.string.are_you_sure))
                            .setMessage(resources.getString(R.string.cloud_sync_is_sure))
                            .setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                                viewModel.cloudSync().observe(viewLifecycleOwner) {
                                    when (it) {
                                        is Async.Error -> {
                                            showToast("Error : ${it.msg}")
                                        }

                                        Async.Loading -> {
                                            showToast("Loading..")
                                        }

                                        is Async.Success -> {
                                            showToast("Success ")
                                        }
                                    }
                                }
                            }
                            .setNegativeButton(resources.getString(R.string.no)) { _, _ ->

                            }.show()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}