package com.kocci.healtikuy.core.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.kocci.healtikuy.core.R
import com.kocci.healtikuy.core.domain.model.Sleep

class NotificationService(private val context: Context) {

    private val manager = context.getSystemService(NotificationManager::class.java)

    fun showNotificationForSleep(sleep: Sleep) {
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(context, SLEEP_CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_edit_24)
            .setContentTitle("Sleep time!")
            .setContentText("Don't forget to sleep!")
            .setSound(soundUri)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        manager.notify(AlarmService.RQC_SLEEP, builder.build())
    }

    fun showNotificationForWater() {
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(context, WATER_CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_edit_24)
            .setContentTitle("Don't forget your water intake!")
            .setContentText("You still have to drink more water!")
            .setSound(soundUri)

        manager.notify(AlarmService.RQC_WATER, builder.build())
    }

    fun showNotificationForExercise(exType: String) {
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(context, EXERCISE_CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_edit_24)
            .setContentTitle("Don't forget your ${exType.lowercase()}!")
            .setContentText("Get points from exercise!!")
            .setSound(soundUri)

        manager.notify(AlarmService.RQC_EXERCISE, builder.build())
    }

    companion object {

        const val WATER_CHANNEL_ID = "water_channel_id"
        const val SUN_CHANNEL_ID = "sun_channel_id"
        const val SLEEP_CHANNEL_ID = "sleep_channel_id"
        const val EXERCISE_CHANNEL_ID = "exercise_channel_id"

        const val WATER_CHANNEL_NAME = "Water Intake"
        const val SUN_CHANNEL_NAME = "Sun Exposure Time"
        const val SLEEP_CHANNEL_NAME = "Sleep Time"
        const val EXERCISE_CHANNEL_NAME = "Exercise Schedule"

        /**
         * Creating channel should be done 1 times after an app creates.
         * can be called within application context
         */
        fun createNotificationChannel(context: Context) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val waterImportance = NotificationManager.IMPORTANCE_LOW
                val sunImportance = NotificationManager.IMPORTANCE_LOW
                val sleepImportance = NotificationManager.IMPORTANCE_HIGH
                val exerciseImportance = NotificationManager.IMPORTANCE_HIGH
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

                val exerciseChannel = NotificationChannel(
                    EXERCISE_CHANNEL_ID,
                    EXERCISE_CHANNEL_NAME,
                    exerciseImportance
                )

                val channel = listOf(waterChannel, sleepChannel, sunChannel, exerciseChannel)
                notificationManager.createNotificationChannels(channel)
            }

        }
    }
}