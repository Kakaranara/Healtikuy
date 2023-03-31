package com.kocci.healtikuy.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.kocci.healtikuy.core.constant.GameRules
import com.kocci.healtikuy.core.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    private object PreferenceKeys {
        //? USER PREFERENCES
        val LAST_LOGIN = longPreferencesKey("last_login")
        val POINTS = longPreferencesKey("points")
        val USERNAME = stringPreferencesKey("username")
        val EMAIL = stringPreferencesKey("email")
        val PHOTO_URL = stringPreferencesKey("photo_url")
        val COIN = intPreferencesKey("coin")

        //? FEATURE PREFERENCES
        val SLEEP_TIME = longPreferencesKey("sleep_time")
    }

    val userPreferences: Flow<UserPreferences> = dataStore.data.map { preferences ->
        val lastLogin = preferences[PreferenceKeys.LAST_LOGIN] ?: System.currentTimeMillis()
        val points = preferences[PreferenceKeys.POINTS] ?: GameRules.FIRST_TIME_POINTS
        val username = preferences[PreferenceKeys.USERNAME] ?: "Username not set"
        val email = preferences[PreferenceKeys.EMAIL] ?: "Email not set"
        val photoUrl =
            preferences[PreferenceKeys.PHOTO_URL] ?: "https://dummyimage.com/600x400/000/fff"
        val coin = preferences[PreferenceKeys.COIN] ?: 0

        UserPreferences(lastLogin, points, coin, username, email, photoUrl)
    }

    val sleepTimePreference: Flow<Long?> = dataStore.data.map { preferences ->
        preferences[PreferenceKeys.SLEEP_TIME]
    }

    suspend fun changeSleepTime(time: Long) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.SLEEP_TIME] = time
        }
    }

    suspend fun addPoints(points: Long) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.POINTS] =
                (preferences[PreferenceKeys.POINTS] ?: GameRules.FIRST_TIME_POINTS) + points
        }
    }

    suspend fun setLastLoginToToday() {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.LAST_LOGIN] = System.currentTimeMillis()
        }
    }
}