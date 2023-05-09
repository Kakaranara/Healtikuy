package com.kocci.healtikuy.core.util.helper

import android.util.Log
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

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

    fun convertDayToMillis(dayTime: Int): Long {
        return (dayInMill * dayTime).toLong()
    }

    fun formatDateString(dateInMillis: Long): String {
        val date = Date(dateInMillis)
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return formatter.format(date)
    }

    fun isTimeWithin1Hours(time: Long): Boolean {
        val calendarForTime = Calendar.getInstance()
        calendarForTime.timeInMillis = time
        val nowTime = LocalDateTime.now()
        val cal2 = Calendar.getInstance()
        cal2.apply {
            set(Calendar.HOUR_OF_DAY, calendarForTime.get(Calendar.HOUR_OF_DAY))
            set(Calendar.MINUTE, calendarForTime.get(Calendar.MINUTE))
        }
        try {
            /**
             * It'd be a problem if the time is between 23.00 - 00.59.
             * because, cal2 get the calendarForTime, only the HOURS and MINUTES.
             * and, cal2 automatically have a NOW date. which isn't right.
             * if cal2 are compared to now local date time, it will be different like 23 Hours.
             * Example : Cal2 = 5 May 2023, 23: 55 -> nowTime = 5 May 2023, 00 : 40
             * The solution for that example is to decrease the cal2 days to 4 May.
             * Or, if this condition true, should i directly return TRUE? HAHAHA
             */
            if (cal2.get(Calendar.HOUR_OF_DAY) == 0 && nowTime.hour == 23) {
                cal2.set(Calendar.DAY_OF_MONTH, cal2.get(Calendar.DAY_OF_MONTH) + 1)
            }
            if (cal2.get(Calendar.HOUR_OF_DAY) == 23 && nowTime.hour == 0) {
                cal2.set(Calendar.DAY_OF_MONTH, cal2.get(Calendar.DAY_OF_MONTH) - 1)
            }
        } catch (e: Exception) {
            Log.e("Date Helper", "isTimeWithin1Hours: ERROR PARSING DATE ${e.message}")
            return true //? because when this happen, probably it should be true..
        }
        val setTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(cal2.timeInMillis), ZoneId.systemDefault())

        val isAfter = setTime.isAfter(nowTime.minusHours(1))
        val isBefore = setTime.isBefore(nowTime.plusHours(1))

        return isAfter && isBefore
//
//        val timeLocalTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalTime()
//        val now = LocalDateTime.now().toLocalDate().atTime(timeLocalTime)
//        return now.isAfter(LocalDateTime.now().minusHours(1)) && now.isBefore(
//            LocalDateTime.now().plusHours(1)
//        )
    }

    fun calculateDayDiff(time: Long, currentTime: Long = System.currentTimeMillis()) : Long{
        val millisDiff = currentTime - time //in millis
        val dayDiff = TimeUnit.DAYS.convert(millisDiff, TimeUnit.MILLISECONDS)

        return dayDiff
    }

    fun getUnixEpoch() = System.currentTimeMillis() / 1000L

}