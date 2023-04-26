package com.kocci.healtikuy.util.helper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

object DrawableHelper {
    @SuppressLint("DiscouragedApi") //! need alternative
    fun getIdentifier(context: Context, name: String): Int {
        return context.resources.getIdentifier(name, "drawable", context.packageName)
    }

    fun getAvatarDrawable(context: Context, name: String): Drawable? {
        return ContextCompat.getDrawable(context, getIdentifier(context, name))
    }
}