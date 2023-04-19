package com.kocci.healtikuy.core.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.receiver.alarm.ExerciseAlarmReceiver
import com.kocci.healtikuy.core.receiver.alarm.SleepAlarmReceiver
import com.kocci.healtikuy.core.receiver.alarm.WaterAlarmReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AlarmService @Inject constructor(@ApplicationContext private val context: Context) {
    private val manager = context.getSystemService(AlarmManager::class.java)

    fun setRepeatingScheduleForSleep(sleepData: Sleep, time: Long) {

        val intent = Intent(context, SleepAlarmReceiver::class.java).apply {
            putExtra(EXTRA_SLEEP_SCHEDULE, sleepData)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            RQC_SLEEP,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        manager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pendingIntent
        )
        Log.e(TAG, "setRepeatingSchedule: SLEEP Alarm SET")
    }

    fun setRepeatingScheduleForWater() {
        val interval3Hours = AlarmManager.INTERVAL_HOUR * 3
        val triggerTime = System.currentTimeMillis() + interval3Hours
        val intent = Intent(context, WaterAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            RQC_WATER,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        manager.setInexactRepeating(AlarmManager.RTC, triggerTime, interval3Hours, pendingIntent)
        Log.e(TAG, "setRepeatingScheduleForWater: WATER Alarm Set")
    }

    fun cancelRepeatingAlarmForWater() {
        val intent = Intent(context, WaterAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            RQC_WATER,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        manager.cancel(pendingIntent)
        Log.e(TAG, "cancelRepeatingAlarmForWater: WATER ALARM CANCEL")
    }

    fun setRepeatingScheduleForCardioExercise(time: Long, intervalInMillis: Long) {
        val intent = Intent(context, ExerciseAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            RQC_EXERCISE,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, time, intervalInMillis, pendingIntent)
    }

    companion object {

        const val EXTRA_SLEEP_SCHEDULE = "extra_sleep_schedule"

        //RQC stands for Request Code
        const val RQC_SLEEP = 5
        const val RQC_WATER = 6
        const val RQC_EXERCISE = 7
        private const val TAG = "AlarmService"
    }
}