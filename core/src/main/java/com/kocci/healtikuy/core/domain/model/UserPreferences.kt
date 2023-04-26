package com.kocci.healtikuy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * User Preference are used to manage preferences only. Managed in : UserPreferenceManager.
 * User Preference are declared () only in preference Manager and first  time services.
 */
@Parcelize
data class UserPreferences(
    var lastLogin: Long,
    var points: Long,
    var coin: Int,
    var username: String,
    var email: String,
    var avatar: String,
    var inventory: Set<String>,
    var running100MPoint: Long,
    var running200MPoint: Long,
    var running400MPoint: Long
) : Parcelable