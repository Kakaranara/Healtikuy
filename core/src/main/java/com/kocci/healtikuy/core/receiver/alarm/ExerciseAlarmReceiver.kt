package com.kocci.healtikuy.core.receiver.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kocci.healtikuy.core.constant.CardioType
import com.kocci.healtikuy.core.service.NotificationService

class ExerciseAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notification = NotificationService(context)
        Log.e(TAG, "onReceive: ALARM EXERCISE RECEIVED", )
        intent?.let { safeIntent ->
            val extras = safeIntent.extras
            when (extras?.getString("name")) {
                CardioType.JOGGING.name -> {
                    notification.showNotificationForExercise(CardioType.JOGGING.name)
                }
                CardioType.RUNNING.name -> {
                    notification.showNotificationForExercise(CardioType.RUNNING.name)
                }
                CardioType.STATIC_BIKE.name -> {
                    notification.showNotificationForExercise(CardioType.STATIC_BIKE.name)
                }
                else -> {
                    notification.showNotificationForExercise("Exercise")
                }
            }
        }
    }
    companion object{
        private const val TAG = "ExerciseAlarmReceiver"
    }
}