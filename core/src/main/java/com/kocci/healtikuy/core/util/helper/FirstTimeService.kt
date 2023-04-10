package com.kocci.healtikuy.core.util.helper

import com.kocci.healtikuy.core.constant.GameRules

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
}