package com.kocci.healtikuy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sleep(
    val id: Int = 0,
    val timeStamp: Long = System.currentTimeMillis(),
    var isCompleted: Boolean = false,
    var timeCompleted: Long = System.currentTimeMillis()
) : Parcelable
