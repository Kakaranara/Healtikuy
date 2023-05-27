package com.kocci.healtikuy.ui.dialog.pick.avatar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kocci.healtikuy.R
import com.kocci.healtikuy.ui.auth.profile.edit.EditProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PickAvatarBSheet : BottomSheetDialogFragment() {

    //! note that this was activityScoped
    private val viewModel: EditProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_bsheet_inventory, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.getStringArrayList(INVENTORY_ARGS)

        args?.let { inventory ->
            val mAdapter = AvatarListAdapter(inventory, requireActivity())
            mAdapter.clickListener = object : AvatarListAdapter.ClickListener {
                override fun setOnItemClick(name: String) {
                    viewModel.changeAvatar(name)
                    dismiss()
                }
            }
            val gridLayout = GridLayoutManager(requireActivity(), 3)
            //set rv
            view.findViewById<RecyclerView>(R.id.rvChangeProfile).apply {
                adapter = mAdapter
                layoutManager = gridLayout
            }
        }

    }

    companion object {
        const val TAG = "ModalBottomSheet"
        const val INVENTORY_ARGS = "inventory_args"
    }
}