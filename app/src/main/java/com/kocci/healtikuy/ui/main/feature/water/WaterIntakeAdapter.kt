package com.kocci.healtikuy.ui.main.feature.water

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kocci.healtikuy.R

class WaterIntakeAdapter(private val item: Int) :
    RecyclerView.Adapter<WaterIntakeAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(item : Int){
//            val dummy = itemView.findViewById<ImageView>(R.id.imgDummy)
//
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_water_intake, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return item
    }
}