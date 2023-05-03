package com.kocci.healtikuy.core.util.helper

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tips(
    private val tips: String,
    private val category: String
) : Parcelable

@Parcelize
data class TipList(
    private val list: List<Tips>
) : Parcelable

object TipsManager {
    //! Still dummy
    fun generateSleepTips() = listOf<String>(
        "8 hours sleep is actually recommended sleep time, but you should not always to look at that.",
        "Kamu",
        "Jangan",
        "Nakal"
    )

    fun generateExerciseTips() = listOf<String>(
        "For Children and Adolescent (aged 5-17 years), should do at least 60 minutes per day physical activity, across the week.",
        "For Adults (aged 18-64 years), should do at least 150-300 minutes of moderate-intensity, or at least 75-150 minutes of vigorous-intensity aerobic physical activity, throughout a week.",

        )

    fun generateSunExposureTips() = listOf<String>(
        "The best time for sunbathing is between 10.00 - 15.00",
        "15 minutes is an optimal duration for sunbathing"
    )
}