package com.kocci.healtikuy.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.ui.auth.profile.edit.EditProfileAdapter
import com.kocci.healtikuy.ui.auth.profile.edit.EditProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModalBottomSheet : BottomSheetDialogFragment() {

    private val viewModel: EditProfileViewModel by activityViewModels()
    var pref: UserPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_bottom_sheet, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref?.let {
            val rv = view.findViewById<RecyclerView>(R.id.rvChangeProfile)
            val adapter = EditProfileAdapter(it.inventory.toList(), requireActivity())
            adapter.listener = object : EditProfileAdapter.Listener {
                override fun setOnItemClick(name: String) {
                    viewModel.changeAvatar(name)
                    dismiss()
                }
            }
            val layoutManager = GridLayoutManager(requireActivity(), 3)

            rv.adapter = adapter
            rv.layoutManager = layoutManager

        }

    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}