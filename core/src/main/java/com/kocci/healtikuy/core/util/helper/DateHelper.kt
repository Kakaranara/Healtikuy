package com.kocci.healtikuy.core.util.helper

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    private const val FULL_DATE_FORMAT = "dd/MM/yyyy, HH:mm"
    private const val TIME_FORMAT = "HH:mm"

    fun isToday(time: Long): Boolean {
        val calendarNow = Calendar.getInstance()
        val calendarSet = Calendar.getInstance()
        calendarSet.time = Date(time)

        return calendarSet.get(Calendar.DAY_OF_MONTH) == calendarNow.get(Calendar.DAY_OF_MONTH)
                && calendarSet.get(Calendar.MONTH) == calendarNow.get(Calendar.MONTH)
                && calendarSet.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR)
    }

    fun showHoursAndMinutes(time: Long): String {
        val dateFormat = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        return dateFormat.format(Date(time))
    }
}