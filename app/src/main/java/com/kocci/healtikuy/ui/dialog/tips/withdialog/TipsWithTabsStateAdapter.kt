package com.kocci.healtikuy.ui.dialog.tips.withdialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kocci.healtikuy.core.util.helper.TipList

class TipsWithTabsStateAdapter(fragment: Fragment, private val tipList: TipList) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = TipsFragment()
        fragment.arguments = Bundle().apply {
            when (position) {
                0 -> {
                    val filteredList = tipList.list.filter { it.category == "tips" }
                    val newTipList = TipList(filteredList)
                    putParcelable(TipsFragment.ARG_LIST_TIPS, newTipList)
                }

                1 -> {
                    val filteredList = tipList.list.filter { it.category == "info" }
                    val newTipList = TipList(filteredList)
                    putParcelable(TipsFragment.ARG_LIST_TIPS, newTipList)
                }
            }
        }
        return fragment
    }
}