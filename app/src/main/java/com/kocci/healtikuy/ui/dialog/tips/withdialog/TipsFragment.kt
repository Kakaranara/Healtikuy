package com.kocci.healtikuy.ui.dialog.tips.withdialog

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kocci.healtikuy.R

class TipsFragment : Fragment(R.layout.fragment_tips) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(ARG_LIST_TIPS) }?.apply {
            val tvTest = view.findViewById<TextView>(R.id.tvTipsTest)
            tvTest.text = getInt(ARG_LIST_TIPS).toString()
        }

    }

    companion object {
        const val ARG_LIST_TIPS = "tip_list"
    }
}