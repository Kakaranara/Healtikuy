package com.kocci.healtikuy.core.util.helper

import com.kocci.healtikuy.core.constant.GameRules
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.core.util.store.Avatar

object FirstTimeService {
    fun getFirstTimeAttributes(): HashMap<String, Any> {
        val inventory = listOf("finn")
        return hashMapOf(
            "avatar" to "finn",
            "inventory" to inventory,
            "coin" to 0,
            "points" to GameRules.FIRST_TIME_POINTS,
            "last_login" to System.currentTimeMillis()
        )
    }

    fun getFirstTimeSync(username: String, email: String): UserPreferences {
        return UserPreferences(
            System.currentTimeMillis(),
            GameRules.FIRST_TIME_POINTS,
            0,
            username,
            email,
            Avatar.FINN.lowerNames,
            setOf(Avatar.FINN.lowerNames)
        )
    }
}