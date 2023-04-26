package com.kocci.healtikuy.core.service

import com.kocci.healtikuy.core.constant.GameRules
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.core.util.store.Avatar

object FirstTimeService {

    val AVATAR: String = Avatar.FINN.lowerNames
    val INVENTORY = listOf(AVATAR)
    const val COIN: Int = 400
    const val POINTS = GameRules.FIRST_TIME_POINTS

    fun getFirstTimeAttributes(): HashMap<String, Any> {
        return hashMapOf(
            "avatar" to AVATAR,
            "inventory" to INVENTORY,
            "coin" to COIN,
            "points" to POINTS,
            "running_100" to 0,
            "running_200" to 0,
            "running_400" to 0,
            "last_login" to System.currentTimeMillis()
        )
    }

    fun getFirstTimeSync(username: String, email: String): UserPreferences {
        return UserPreferences(
            lastLogin = System.currentTimeMillis(),
            points = GameRules.FIRST_TIME_POINTS,
            coin = COIN,
            username = username,
            email = email,
            avatar = AVATAR,
            inventory = setOf(AVATAR)
        )
    }
}