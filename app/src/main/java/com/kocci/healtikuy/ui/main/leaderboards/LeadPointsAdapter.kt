package com.kocci.healtikuy.ui.main.leaderboards

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsPoint
import com.kocci.healtikuy.util.helper.DrawableHelper

class LeadPointsAdapter(
    private val data: List<LeaderboardsPoint>,
    private val context: Context,
    private val unit: String? = null
) : RecyclerView.Adapter<LeadPointsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: LeaderboardsPoint) {
            val position: TextView = itemView.findViewById(R.id.tvLeadNumber)
            val avatar: ImageView = itemView.findViewById(R.id.imgLeadAvatar)
            val username: TextView = itemView.findViewById(R.id.tvLeadAvatar)
            val points: TextView = itemView.findViewById(R.id.tvLeadPoints)

            position.text = data.position.toString()
            avatar.setImageDrawable(
                DrawableHelper.getAvatarDrawable(context, data.avatar)
            )
            username.text = data.name

            unit?.let { unit ->
                points.text = "${data.points} $unit"

            } ?: run {
                points.text = data.points.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboards, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}