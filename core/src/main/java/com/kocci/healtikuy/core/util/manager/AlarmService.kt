package com.kocci.healtikuy.core.util.manager

import android.app.AlarmManager
import android.content.Context

class AlarmService(val context: Context) {
    fun set(){
        val alarmManager = context.getSystemService(AlarmManager::class.java)
    }
}