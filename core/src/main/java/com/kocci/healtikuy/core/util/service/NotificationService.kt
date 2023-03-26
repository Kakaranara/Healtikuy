package com.kocci.healtikuy.core.util.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class NotificationService {

    companion object {

        const val WATER_CHANNEL_ID = "water_channel_id"
        const val SUN_CHANNEL_ID = "sun_channel_id"
        const val SLEEP_CHANNEL_ID = "sleep_channel_id"

        const val WATER_CHANNEL_NAME = "Water Intake"
        const val SUN_CHANNEL_NAME = "Sun Exposure Time"
        const val SLEEP_CHANNEL_NAME = "Sleep Time"

        /**
         * Creating channel should be done 1 times after an app creates
         * can be called within application context
         */
        fun createNotificationChannel(context: Context) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val waterImportance = NotificationManager.IMPORTANCE_LOW
                val sunImportance = NotificationManager.IMPORTANCE_LOW
                val sleepImportance = NotificationManager.IMPORTANCE_HIGH
                val notificationManager = context.getSystemService(NotificationManager::class.java)

                val waterChannel = NotificationChannel(
                    WATER_CHANNEL_ID,
                    WATER_CHANNEL_NAME,
                    waterImportance
                )

                val sleepChannel = NotificationChannel(
                    SLEEP_CHANNEL_ID,
                    SLEEP_CHANNEL_NAME,
                    sleepImportance
                )

                val sunChannel = NotificationChannel(
                    SUN_CHANNEL_ID,
                    SUN_CHANNEL_NAME,
                    sunImportance
                )

                val channel = listOf(waterChannel, sleepChannel, sunChannel)
                notificationManager.createNotificationChannels(channel)
            }

        }
    }
}