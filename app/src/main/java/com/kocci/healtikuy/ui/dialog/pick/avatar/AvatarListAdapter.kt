package com.kocci.healtikuy.ui.dialog.pick.avatar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kocci.healtikuy.R
import com.kocci.healtikuy.util.helper.DrawableHelper

class AvatarListAdapter(private val list: List<String>, private val context: Context) :
    RecyclerView.Adapter<AvatarListAdapter.ViewHolder>() {

    var clickListener: ClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(name: String) {
            val avatarView = itemView.findViewById<ImageView>(R.id.imgProfileItemAvatar)
            val tv = itemView.findViewById<TextView>(R.id.tvProfileItemName)
            val card = itemView.findViewById<CardView>(R.id.cardProfileItemAvatar)
            val drawable = ContextCompat.getDrawable(
                context,
                DrawableHelper.getIdentifier(context, name)
            )
            avatarView.setImageDrawable(drawable)
            tv.text = name.replace("_"," ")
            card.setOnClickListener {
                clickListener?.setOnItemClick(name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile_change_avatar, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
    interface ClickListener {
        fun setOnItemClick(name: String)
    }
}
