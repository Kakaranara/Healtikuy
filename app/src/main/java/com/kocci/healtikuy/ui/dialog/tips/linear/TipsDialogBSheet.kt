package com.kocci.healtikuy.ui.dialog.tips.linear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.util.helper.TipList
import com.kocci.healtikuy.ui.dialog.tips.adapter.TipsDialogAdapter

/**
 * Just a bottom sheet with linear recycler view.
 */
class TipsDialogBSheet(private val tipList: TipList) : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_bsheet_tips, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tipList = tipList.list

        val rv: RecyclerView = view.findViewById(R.id.rvTips)
        rv.adapter = TipsDialogAdapter(tipList)
        rv.layoutManager = LinearLayoutManager(requireActivity())


    }
}