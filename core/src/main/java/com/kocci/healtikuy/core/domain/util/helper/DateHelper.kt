package com.kocci.healtikuy.core.domain.util.helper

import java.util.*

object DateHelper {
    fun isToday(time: Long): Boolean {
        val calendarNow = Calendar.getInstance()
        val calendarSet = Calendar.getInstance()
        calendarSet.time = Date(time)

        return calendarSet.get(Calendar.DAY_OF_MONTH) == calendarNow.get(Calendar.DAY_OF_MONTH)
                && calendarSet.get(Calendar.MONTH) == calendarNow.get(Calendar.MONTH)
                && calendarSet.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR)
    }
}