package com.kocci.healtikuy.ui.main.store

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kocci.healtikuy.MyApplication
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.util.store.CharacterInStore

class StoreAdapter(private val item: List<CharacterInStore>, private val activityContext: Context) :
    RecyclerView.Adapter<StoreAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CharacterInStore) {
            val name: TextView = itemView.findViewById(R.id.tvStoreItemName)
            val image: ImageView = itemView.findViewById(R.id.imgStoreItemAvatar)
            val price: TextView = itemView.findViewById(R.id.tvStoreItemPrice)

            name.text = item.name
            price.text = item.price.toString()

            val resources = activityContext.resources
            val drawable = ContextCompat.getDrawable(
                activityContext,
                resources.getIdentifier(item.name, "drawable", activityContext.packageName)
            )

            Glide.with(activityContext).load(drawable).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])
    }
}