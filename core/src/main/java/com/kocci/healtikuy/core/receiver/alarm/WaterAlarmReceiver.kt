package com.kocci.healtikuy.core.receiver.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kocci.healtikuy.core.util.service.NotificationService

class WaterAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.e(TAG, "onReceive: WATER BROADCAST RECEIVED", )
        val notification = NotificationService(context)
        notification.showNotificationForWater()
    }

    companion object{
        private const val TAG = "WaterAlarmReceiver"
    }
}