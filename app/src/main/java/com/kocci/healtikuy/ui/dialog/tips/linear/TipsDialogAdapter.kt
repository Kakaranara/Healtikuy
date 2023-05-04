package com.kocci.healtikuy.ui.dialog.tips.linear

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.util.helper.Tips
import com.kocci.healtikuy.util.extension.gone

class TipsDialogAdapter(
    private val list: List<Tips>,
    private val withoutTitle: Boolean = false,
) : RecyclerView.Adapter<TipsDialogAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Tips) {
            val tvTips = itemView.findViewById<TextView>(R.id.tvItemTips)
            val tvTitleTips = itemView.findViewById<TextView>(R.id.tvItemTitleTips)
            if (withoutTitle) {
                tvTitleTips.gone()
                tvTips.text = data.content
            } else {
                tvTips.text = data.content
                tvTitleTips.text = data.category
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tips_rv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}