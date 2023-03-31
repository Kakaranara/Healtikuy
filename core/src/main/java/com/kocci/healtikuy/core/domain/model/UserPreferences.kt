package com.kocci.healtikuy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserPreferences(
    var lastLogin: Long,
    var points: Long,
    var coin: Int,
    var username: String,
    var email: String,
    var photoUrl: String,
) : Parcelable