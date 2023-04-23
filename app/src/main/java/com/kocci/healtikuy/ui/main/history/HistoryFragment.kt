package com.kocci.healtikuy.ui.main.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ExpandableListView
import android.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.kocci.healtikuy.R

class HistoryFragment : Fragment(R.layout.fragment_history) {

    //val args
    private val args: HistoryFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbarHistory)
        toolbar.setupWithNavController(findNavController())
        val list = args.history
        val groupList = list.groupList
        val itemList = list.itemList

        val expandableListView: ExpandableListView = view.findViewById(R.id.elvHistory)
        val adapter = HistoryAccordionAdapter(requireActivity(), groupList, itemList)

        expandableListView.setAdapter(adapter)
    }

}