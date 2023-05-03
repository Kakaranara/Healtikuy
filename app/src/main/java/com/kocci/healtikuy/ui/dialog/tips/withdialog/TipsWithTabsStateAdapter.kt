package com.kocci.healtikuy.ui.dialog.tips.withdialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TipsWithTabsStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = TipsFragment()
        fragment.arguments = Bundle().apply {
            putInt(TipsFragment.ARG_LIST_TIPS, position + 1)
        }
        return fragment
    }
}