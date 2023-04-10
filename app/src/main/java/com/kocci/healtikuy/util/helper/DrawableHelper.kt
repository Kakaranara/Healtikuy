package com.kocci.healtikuy.util.helper

import android.annotation.SuppressLint
import android.content.Context

object DrawableHelper {
    @SuppressLint("DiscouragedApi") //! need alternative
    fun getIdentifier(context: Context, name: String): Int {
        return context.resources.getIdentifier(name, "drawable", context.packageName)
    }
}