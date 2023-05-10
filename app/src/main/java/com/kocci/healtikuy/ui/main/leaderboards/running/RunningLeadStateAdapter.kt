package com.kocci.healtikuy.ui.main.leaderboards.running

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class RunningLeadStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = RunningLeadTabFragment();
        fragment.arguments = Bundle().apply {
            when (position) {
                0 -> {
                    putString(RunningLeadTabFragment.RUNNING_TYPE, "100")
                }

                1 -> {
                    putString(RunningLeadTabFragment.RUNNING_TYPE, "200")
                }

                2 -> {
                    putString(RunningLeadTabFragment.RUNNING_TYPE, "400")
                }
            }
        }
        return fragment
    }
}