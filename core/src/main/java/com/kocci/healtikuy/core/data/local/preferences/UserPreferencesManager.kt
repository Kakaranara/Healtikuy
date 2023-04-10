package com.kocci.healtikuy.core.data.local.preferences

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.kocci.healtikuy.core.constant.GameRules
import com.kocci.healtikuy.core.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserPreferencesManager @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    private object PreferenceKeys {
        //? USER PREFERENCES
        val LAST_LOGIN = longPreferencesKey("last_login")
        val POINTS = longPreferencesKey("points")
        val USERNAME = stringPreferencesKey("username")
        val EMAIL = stringPreferencesKey("email")
        val AVATAR = stringPreferencesKey("avatar")
        val COIN = intPreferencesKey("coin")
        val INVENTORY = stringSetPreferencesKey("inventory")

        //? FEATURE PREFERENCES
        val SLEEP_TIME = longPreferencesKey("sleep_time")
    }

    val userPreferences: Flow<UserPreferences> = dataStore.data.map { preferences ->
        val lastLogin = preferences[PreferenceKeys.LAST_LOGIN] ?: System.currentTimeMillis()
        val points = preferences[PreferenceKeys.POINTS] ?: GameRules.FIRST_TIME_POINTS
        val username = preferences[PreferenceKeys.USERNAME] ?: "Username not set"
        val email = preferences[PreferenceKeys.EMAIL] ?: "Email not set"
        val avatar =
            preferences[PreferenceKeys.AVATAR] ?: "finn"
        val coin = preferences[PreferenceKeys.COIN] ?: 0
        val inventory = preferences[PreferenceKeys.INVENTORY] ?: setOf()

        val userPref =
            UserPreferences(lastLogin, points, coin, username, email, avatar, inventory)
        Log.e(TAG, "prefManager: $userPref")
        return@map userPref
    }

    val sleepTimePreference: Flow<Long?> = dataStore.data.map { preferences ->
        preferences[PreferenceKeys.SLEEP_TIME]
    }

    suspend fun updateUserProfile(username: String, avatar: String, email: String) {
        dataStore.edit { pref ->
            pref[PreferenceKeys.EMAIL] = email
            pref[PreferenceKeys.USERNAME] = username
            pref[PreferenceKeys.AVATAR] = avatar
        }
    }

    suspend fun changeInventory(item: Set<String>) {
        dataStore.edit { pref ->
            pref[PreferenceKeys.INVENTORY] = item
        }
    }

    suspend fun changeCoin(item: Int){
        dataStore.edit { pref ->
            pref[PreferenceKeys.COIN] = item
        }
    }

    suspend fun changeSleepTime(time: Long) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.SLEEP_TIME] = time
        }
    }

    suspend fun loginSync(userPreferences: UserPreferences) {
        dataStore.edit { pref ->
            pref[PreferenceKeys.EMAIL] = userPreferences.email
            pref[PreferenceKeys.USERNAME] = userPreferences.username
            pref[PreferenceKeys.AVATAR] = userPreferences.avatar
            pref[PreferenceKeys.COIN] = userPreferences.coin
            pref[PreferenceKeys.POINTS] = userPreferences.points
            pref[PreferenceKeys.INVENTORY] = userPreferences.inventory
            pref[PreferenceKeys.LAST_LOGIN] = userPreferences.lastLogin
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

    suspend fun clearAllData() {
        dataStore.edit {
            it.clear()
        }
    }

    companion object {
        private const val TAG = "UserPreferencesManager"
    }
}