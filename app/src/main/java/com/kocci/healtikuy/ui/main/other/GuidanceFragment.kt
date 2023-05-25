package com.kocci.healtikuy.ui.main.other

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.kocci.healtikuy.R

class GuidanceFragment : Fragment(R.layout.fragment_guidance) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbarGuidance)

        toolbar.setupWithNavController(findNavController())
    }
}