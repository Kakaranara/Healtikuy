package com.kocci.healtikuy.util.extension

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(text: String, longToast: Boolean = false) {
    activity?.let {
        if (longToast) {
            Toast.makeText(it, text, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(it, text, Toast.LENGTH_SHORT).show()
        }
    } ?: Log.e("Toast", "ACTIVITY IS NULL")
}