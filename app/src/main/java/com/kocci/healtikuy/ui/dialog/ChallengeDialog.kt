package com.kocci.healtikuy.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kocci.healtikuy.R
import com.kocci.healtikuy.ui.main.challenges.ChallengeAdapter
import com.kocci.healtikuy.core.domain.model.Challenges

class ChallengeDialog(context: Context) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireActivity())
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_challenges, null)
        val rv = view.findViewById<RecyclerView>(R.id.rvChallenges)
        val adapter = ChallengeAdapter(generateList())
        val layoutManager = LinearLayoutManager(requireActivity())
        rv.adapter = adapter
        rv.layoutManager = layoutManager

        builder.setView(view)
        return builder.create()
    }

    fun generateList(): List<Challenges> {
        return List(20) {
            Challenges(it, "Reach points > ${it} HALO hanya sebagai penambah kata", false)
        }
    }
}