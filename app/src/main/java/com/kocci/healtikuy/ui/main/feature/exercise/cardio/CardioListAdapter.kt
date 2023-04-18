package com.kocci.healtikuy.ui.main.feature.exercise.cardio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.usecase.CardioStatus

class CardioListAdapter(private val list: List<CardioStatus>) :
    RecyclerView.Adapter<CardioListAdapter.ViewHolder>() {

    interface ClickListener {
        fun OnCardioClick(data: CardioStatus)
    }

    var listener: ClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: CardioStatus) {
            val title: TextView = itemView.findViewById(R.id.tvCardioItem)
            val checkList: ImageView = itemView.findViewById(R.id.checkListCardioItem)
            val icon: ImageView = itemView.findViewById(R.id.iconExercise)

            when (data.name) {
                CardioListFragment.STATIC_BIKE -> {
                    setDrawable(icon, R.drawable.icon_running)
                }
            }

            title.text = data.name
            checkList.visibility = if (data.isCompleted) View.VISIBLE else View.INVISIBLE

            itemView.rootView.setOnClickListener {
                listener?.OnCardioClick(data)
            }
        }
    }

    fun setDrawable(view: ImageView, resId: Int) {
        view.setImageDrawable(ContextCompat.getDrawable(view.context, resId))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_cardio, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}