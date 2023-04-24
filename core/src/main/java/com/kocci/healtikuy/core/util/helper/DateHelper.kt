package com.kocci.healtikuy.core.util.helper

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    const val FULL_DATE_FORMAT = "dd/MM/yyyy, HH:mm"
    const val DATE_FORMAT = "dd MMMM yyyy"
    const val TIME_FORMAT = "HH:mm"

    const val secondInMill = 1000
    const val minuteInMill = secondInMill * 60
    const val hourInMill = minuteInMill * 60
    const val dayInMill = hourInMill * 24

    fun dayTimeElapsed(from: Long): Int {
        val timeNow = System.currentTimeMillis()
        val timeDifferent = timeNow - from

        val dayElapsed = timeDifferent / dayInMill

        return dayElapsed.toInt()
    }

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

    fun convertTimeStringToLong(str: String): Long {
        val dateFormat = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        return dateFormat.parse(str)!!.time
    }

    fun convertTimeStringToTodayDate(time: String): Long {
        val timeFormat = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        val timeTemp = timeFormat.parse(time)

        val cal = Calendar.getInstance()
        val cal2 = Calendar.getInstance()

        cal2.time = timeTemp as Date
        cal.apply {
            set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY))
            set(Calendar.MINUTE, cal2.get(Calendar.MINUTE))
        }
        return cal.time.time
    }

    fun formatDateString(dateInMillis: Long): String {
        val date = Date(dateInMillis)
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return formatter.format(date)
    }
}