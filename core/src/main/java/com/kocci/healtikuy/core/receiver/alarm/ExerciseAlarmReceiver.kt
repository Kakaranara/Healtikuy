package com.kocci.healtikuy.core.receiver.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kocci.healtikuy.core.service.NotificationService

class ExerciseAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notification = NotificationService(context)

    }
}