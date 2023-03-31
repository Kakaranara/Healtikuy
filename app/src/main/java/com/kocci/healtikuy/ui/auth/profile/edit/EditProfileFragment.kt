package com.kocci.healtikuy.ui.auth.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.databinding.FragmentEditProfileBinding
import com.kocci.healtikuy.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EditProfileViewModel by viewModels()
    private val args by navArgs<EditProfileFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarEditProfile.setupWithNavController(findNavController())
        val userPreferences = args.userdata

        binding.etUsernameEditProfile.setText(userPreferences.username)

        binding.btnSubmit.setOnClickListener {
            val username = binding.etUsernameEditProfile.text.toString()
            userPreferences.username = username
            viewModel.updateProfile(userPreferences).observe(viewLifecycleOwner) {
                when (it) {
                    is Async.Error -> {
                        showToast("Error")
                    }
                    Async.Loading -> {

                        showToast("Loading")
                    }
                    is Async.Success -> {
                        showToast("success")
                        findNavController().popBackStack()
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
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}