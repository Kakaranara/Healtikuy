package com.kocci.healtikuy.ui.dialog.tips.withdialog

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kocci.healtikuy.R

/**
 * Tabs contains 2 tabs.
 * Tips and Info
 * Info : can be a benefit, or anything.
 */
class TipsBSheetDialogWithTabs : BottomSheetDialogFragment(R.layout.dialog_tips_with_tabs) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TipsWithTabsStateAdapter(this)
        val viewPager: ViewPager2 = view.findViewById(R.id.viewPagerTest)
        viewPager.adapter = adapter

        val tabLayout: TabLayout = view.findViewById(R.id.tabLayoutTest)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0) "Tips" else "Info"
        }.attach()
    }

}