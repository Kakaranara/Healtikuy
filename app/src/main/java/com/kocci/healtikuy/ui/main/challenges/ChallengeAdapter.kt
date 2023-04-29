package com.kocci.healtikuy.ui.main.challenges

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.Challenge
import com.kocci.healtikuy.core.domain.model.UserPreferences

class ChallengeAdapter(
    private val list: List<Challenge>,
    private val userPreferences: UserPreferences
) :
    RecyclerView.Adapter<ChallengeAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Challenge) {
            val tvName = itemView.findViewById<TextView>(R.id.tvItemChallengesName)
            val tvBonusCoin = itemView.findViewById<TextView>(R.id.tvItemChallengeBonusCoin)
            val btnComplete = itemView.findViewById<Button>(R.id.btnItemChallenges)


            tvName.text = data.name
            tvBonusCoin.text = "Bonus Coin: ${data.coinRewards}"


            if (data.category == "points") {
                if (userPreferences.points >= data.pointRequired) {
                    btnComplete.isEnabled = true
                }
            } else {
                btnComplete.isEnabled = false
            }

            if (data.isCompleted) {
                //TODO : Change with checkmark icon
                btnComplete.isEnabled = false
            }

            btnComplete.setOnClickListener {
                clickListener?.onButtonComplete(data)
                btnComplete.isEnabled = false //TODO : Change with checkmark icon
            }
        }
    }

    var clickListener: OnButtonClickListener? = null

    interface OnButtonClickListener {
        fun onButtonComplete(data: Challenge)
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