package com.kocci.healtikuy.ui.main.challenges

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.challenges.Challenge
import com.kocci.healtikuy.core.domain.model.challenges.UserChallengeAttributes
import com.kocci.healtikuy.util.extension.gone
import com.kocci.healtikuy.util.extension.visible

class ChallengeAdapter(
    private val list: List<Challenge>,
    private val userAttr: UserChallengeAttributes
) :
    RecyclerView.Adapter<ChallengeAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Challenge) {
            val tvName = itemView.findViewById<TextView>(R.id.tvItemChallengesName)
            val tvBonusCoin = itemView.findViewById<TextView>(R.id.tvItemChallengeBonusCoin)
            val btnComplete = itemView.findViewById<Button>(R.id.btnItemChallenges)
            val checkmark = itemView.findViewById<ImageView>(R.id.imgItemChallengeCheckmark)

            tvName.text = data.name
            tvBonusCoin.text = itemView.resources.getString(R.string.bonus_coin_d, data.coinRewards)

            when (data.category) {
                "points" -> {
                    if (userAttr.points >= data.pointRequired) {
                        btnComplete.isEnabled = true
                    }
                }

                "login" -> {
                    if (userAttr.loginStreak >= data.loginRequired) {
                        btnComplete.isEnabled = true
                    }
                }

                else -> btnComplete.isEnabled = false
            }

            if (data.isCompleted) {
                btnComplete.gone()
                checkmark.visible()
            }

            btnComplete.setOnClickListener {
                clickListener?.onButtonComplete(data)
                btnComplete.gone()
                checkmark.visible()
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