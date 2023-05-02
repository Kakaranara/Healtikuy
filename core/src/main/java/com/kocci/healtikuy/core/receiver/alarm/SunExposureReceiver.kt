package com.kocci.healtikuy.core.receiver.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kocci.healtikuy.core.service.NotificationService

class SunExposureReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.e("SUN EX", "onReceive: BROADCAST RECEIVED")
        val notification = NotificationService(context)
        notification.showNotificationForSunExposure()
    }
}