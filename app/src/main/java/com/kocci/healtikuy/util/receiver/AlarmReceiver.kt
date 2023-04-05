package com.kocci.healtikuy.util.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.util.getParcel
import com.kocci.healtikuy.core.receiver.alarm.AlarmService
import com.kocci.healtikuy.core.util.service.NotificationService

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val sleep: Sleep? = intent?.getParcel(AlarmService.EXTRA_SLEEP_SCHEDULE)

        if (sleep != null) {
            if (!sleep.isCompleted) {
                val notification = NotificationService(context)
                notification.showNotification(sleep)
            }
        }
    }
}