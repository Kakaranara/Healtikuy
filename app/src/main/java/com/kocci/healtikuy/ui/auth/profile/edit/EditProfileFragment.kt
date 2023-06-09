package com.kocci.healtikuy.ui.auth.profile.edit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.databinding.FragmentEditProfileBinding
import com.kocci.healtikuy.ui.dialog.pick.avatar.PickAvatarBSheet
import com.kocci.healtikuy.util.extension.showToast
import com.kocci.healtikuy.util.helper.DrawableHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EditProfileViewModel by activityViewModels()
    private val args by navArgs<EditProfileFragmentArgs>()

    companion object {
        private const val TAG = "EditProfileFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarEditProfile.setupWithNavController(findNavController())
        val userPreferences = args.userdata

        viewModel.changeAvatar(userPreferences.avatar)

        viewModel.avatar.observe(viewLifecycleOwner) {
            binding.imgAvatarEditProfile.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    DrawableHelper.getIdentifier(requireActivity(), it)
                )
            )
        }

        binding.etUsernameEditProfile.setText(userPreferences.username)


        binding.btnChangeAvatar.setOnClickListener {
            val inventory = ArrayList(userPreferences.inventory)
            val bundle = Bundle().apply {
                putStringArrayList(PickAvatarBSheet.INVENTORY_ARGS, inventory)
            }
            val pickAvatarBSheet = PickAvatarBSheet().apply {
                arguments = bundle
            }
            pickAvatarBSheet.show(childFragmentManager, PickAvatarBSheet.TAG)
        }

        binding.btnSubmit.setOnClickListener {
            val username = binding.etUsernameEditProfile.text.toString()
            userPreferences.username = username
            userPreferences.avatar = viewModel.avatar.value ?: "finn"
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

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
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