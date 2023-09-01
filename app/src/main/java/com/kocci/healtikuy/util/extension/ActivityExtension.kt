package com.kocci.healtikuy.util.extension

import android.app.Activity
import android.widget.Toast

fun Activity.showToast(message: String, longToast: Boolean = false) {
    if (longToast) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    } else {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}