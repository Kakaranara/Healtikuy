package com.kocci.healtikuy.ui.dialog.tips.withdialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.util.extension.parcelable
import com.kocci.healtikuy.core.util.helper.TipList
import com.kocci.healtikuy.ui.dialog.tips.linear.TipsDialogAdapter

class TipsFragment : Fragment(R.layout.fragment_tips) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(ARG_LIST_TIPS) }?.apply {
            val args = parcelable<TipList>(ARG_LIST_TIPS)

            args?.let {
                val rv = view.findViewById<RecyclerView>(R.id.rvItemFragmentTips)
                rv.apply {
                    adapter = TipsDialogAdapter(it.list)
                    layoutManager = LinearLayoutManager(requireActivity())
                }
            }
        }

    }

    companion object {
        const val ARG_LIST_TIPS = "tip_list"
    }
}