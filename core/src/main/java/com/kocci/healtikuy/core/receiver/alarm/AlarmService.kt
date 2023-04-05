package com.kocci.healtikuy.core.receiver.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.util.getParcel
import com.kocci.healtikuy.core.util.service.NotificationService
import javax.inject.Inject

class AlarmService @Inject constructor() : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val sleep: Sleep? = intent?.getParcel(EXTRA_SLEEP_SCHEDULE)
        Log.e(TAG, "onReceive: BROADCAST RECEIVED")

        if (sleep != null) {
            if (!sleep.isCompleted) {
                val notification = NotificationService(context)
                notification.showNotification(sleep)
            }
        }
    }

    fun setRepeatingSchedule(context: Context, sleepData: Sleep, time: Long) {
        val manager = context.getSystemService(AlarmManager::class.java)

        val intent = Intent(context, AlarmService::class.java).apply {
            putExtra(EXTRA_SLEEP_SCHEDULE, sleepData)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        manager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pendingIntent
        )
        Log.e(TAG, "setRepeatingSchedule: Alarm SET")
    }

    companion object {

        const val EXTRA_SLEEP_SCHEDULE = "extra_sleep_schedule"
        private const val TAG = "AlarmService"
    }
}

