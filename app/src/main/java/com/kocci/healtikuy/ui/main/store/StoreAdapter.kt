package com.kocci.healtikuy.ui.main.store

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.util.store.CharacterInStore

class StoreAdapter(private val item: List<CharacterInStore>, private val activityContext: Context) :
    RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    interface Listener {
        fun onItemClick(item: CharacterInStore)
    }

    var listener: Listener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CharacterInStore) {
            val name: TextView = itemView.findViewById(R.id.imgStoreItemName2)
            val image: ImageView = itemView.findViewById(R.id.imgStoreItemAvatar2)
            val price: TextView = itemView.findViewById(R.id.imgStoreItemPrice2)
            val card: CardView = itemView.findViewById(R.id.cardStoreItemAvatar)

            name.text = item.name.replace("_", " ")
            price.text = item.price.toString()

            val resources = activityContext.resources
            val drawable = ContextCompat.getDrawable(
                activityContext,
                resources.getIdentifier(item.name, "drawable", activityContext.packageName)
            )

            Glide.with(activityContext).load(drawable).into(image)
            card.setOnClickListener {
                listener?.onItemClick(CharacterInStore(item.name, item.price))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_store_gridversion, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])
    }
}