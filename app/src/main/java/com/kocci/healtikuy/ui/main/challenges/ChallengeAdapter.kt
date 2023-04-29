package com.kocci.healtikuy.ui.main.challenges

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.Challenge

class ChallengeAdapter(private val list: List<Challenge>) :
    RecyclerView.Adapter<ChallengeAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Challenge) {
            val tv = itemView.findViewById<TextView>(R.id.tvItemChallengesName)
            val btn = itemView.findViewById<Button>(R.id.btnItemChallenges)

            tv.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_challenges, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}