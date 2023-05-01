package com.kocci.healtikuy.core.receiver.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.util.getParcel
import com.kocci.healtikuy.core.service.AlarmService.Companion.EXTRA_SLEEP_SCHEDULE
import com.kocci.healtikuy.core.service.NotificationService

class SleepAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val sleep: Sleep? = intent?.getParcel(EXTRA_SLEEP_SCHEDULE)
        Log.e(TAG, "onReceive: BROADCAST RECEIVED")

//        if (sleep != null) {
//            if (!sleep.isCompleted) {
                val notification = NotificationService(context)
                notification.showNotificationForSleep()
//            }
//        }
    }

    companion object {
        private const val TAG = "SleepAlarmReceiver"
    }
}

