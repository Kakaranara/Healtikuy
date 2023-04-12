package com.kocci.healtikuy

import android.app.Application
import com.kocci.healtikuy.core.service.NotificationService
import dagger.hilt.android.HiltAndroidApp

//? Generate Hilt file for Injection
@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationService.createNotificationChannel(applicationContext)
    }
}