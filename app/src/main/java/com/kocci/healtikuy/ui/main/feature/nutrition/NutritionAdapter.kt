package com.kocci.healtikuy.ui.main.feature.nutrition

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.Nutrition

class NutritionAdapter(private val context: Context, private val list: List<Nutrition>) :
    RecyclerView.Adapter<NutritionAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Nutrition) {
            val tvNutrition = itemView.findViewById<TextView>(R.id.tvItemNutrition)
            val img: ImageView = itemView.findViewById(R.id.imgItemNutrition)
            tvNutrition.text = data.foodName

            if (data.category == "fruits") {
                img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.apple))
            } else {
                img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.vegetable))
            }
        }
    }

    private fun getDrawable(context: Context, drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawableRes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_nutrition, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}