package com.kocci.healtikuy.core.data.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.kocci.healtikuy.core.constant.GameRules
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

data class UserPreferences(
    val lastLogin: Long,
    val points: Long
)

private object PreferenceKeys {
    val LAST_LOGIN = longPreferencesKey("last_login")
    val POINTS = longPreferencesKey("points")
}

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val userPreferences: Flow<UserPreferences> = dataStore.data.map { preferences ->
        val lastLogin = preferences[PreferenceKeys.LAST_LOGIN] ?: System.currentTimeMillis()
        val points = preferences[PreferenceKeys.POINTS] ?: GameRules.FIRST_TIME_POINTS
        UserPreferences(lastLogin, points)
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