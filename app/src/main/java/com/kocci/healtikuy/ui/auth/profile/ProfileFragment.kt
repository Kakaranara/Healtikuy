package com.kocci.healtikuy.ui.auth.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var userPreference: UserPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarProfile.setupWithNavController(findNavController())

        viewModel.userData.observe(viewLifecycleOwner) { userData ->
            userPreference = userData
            binding.apply {
                tvProfileEmail.text = userData.email
                tvProfileName.text = userData.username
            }
        }

        binding.btnEditProfile.setOnClickListener {
            val gotoEdit =
                ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(userPreference)
            findNavController().navigate(gotoEdit)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}