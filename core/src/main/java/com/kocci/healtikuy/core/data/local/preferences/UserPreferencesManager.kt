package com.kocci.healtikuy.core.data.local.preferences

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.kocci.healtikuy.core.constant.GameRules
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.core.domain.model.exercise.ExerciseTimePreferences
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

        //? Exercise PREFERENCE
        val JOGGING_TIME = longPreferencesKey("jogging_time")
        val JOGGING_INTERVAL = intPreferencesKey("jogging_interval")
        val JOGGING_IS_EDITING = booleanPreferencesKey("jogging_is_editing")

        val RUNNING_TIME = longPreferencesKey("running_time")
        val RUNNING_INTERVAL = intPreferencesKey("running_interval")
        val RUNNING_IS_EDITING = booleanPreferencesKey("running_is_editing")

        val STATIC_BIKE_TIME = longPreferencesKey("static_bike_time")
        val STATIC_BIKE_INTERVAL = intPreferencesKey("static_bike_interval")
        val STATIC_BIKE_IS_EDITING = booleanPreferencesKey("static_bike_is_editing")
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

    val joggingTimePreference: Flow<ExerciseTimePreferences> = dataStore.data.map { pref ->
        val time = pref[PreferenceKeys.JOGGING_TIME]
        val interval = pref[PreferenceKeys.JOGGING_INTERVAL]
        val isEditing = pref[PreferenceKeys.JOGGING_IS_EDITING] ?: false

        ExerciseTimePreferences(time, interval, isEditing)
    }

    val runningTimePreference: Flow<ExerciseTimePreferences> = dataStore.data.map { pref ->
        val time = pref[PreferenceKeys.RUNNING_TIME]
        val interval = pref[PreferenceKeys.RUNNING_INTERVAL]
        val isEditing = pref[PreferenceKeys.RUNNING_IS_EDITING] ?: false

        ExerciseTimePreferences(time, interval, isEditing)
    }


    val staticBikeTimePreference: Flow<ExerciseTimePreferences> = dataStore.data.map { pref ->
        val time = pref[PreferenceKeys.STATIC_BIKE_TIME]
        val interval = pref[PreferenceKeys.STATIC_BIKE_INTERVAL]
        val isEditing = pref[PreferenceKeys.STATIC_BIKE_IS_EDITING] ?: false

        ExerciseTimePreferences(time, interval, isEditing)
    }

    suspend fun updateUserProfile(username: String, avatar: String) {
        dataStore.edit { pref ->
            pref[PreferenceKeys.USERNAME] = username
            pref[PreferenceKeys.AVATAR] = avatar
        }
    }

    suspend fun changeInventory(item: Set<String>) {
        dataStore.edit { pref ->
            pref[PreferenceKeys.INVENTORY] = item
        }
    }

    suspend fun changeCoin(item: Int) {
        dataStore.edit { pref ->
            pref[PreferenceKeys.COIN] = item
        }
    }

    suspend fun changeSleepTime(time: Long) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.SLEEP_TIME] = time
        }
    }

    suspend fun changeJoggingTime(time: Long, interval: Int, isEditing: Boolean = false) {
        dataStore.edit { pref ->
            pref[PreferenceKeys.JOGGING_TIME] = time
            pref[PreferenceKeys.JOGGING_INTERVAL] = interval
            pref[PreferenceKeys.JOGGING_IS_EDITING] = isEditing
        }
    }

    suspend fun editJoggingTime(isEditing: Boolean = false) {
        dataStore.edit { pref ->
            pref[PreferenceKeys.JOGGING_IS_EDITING] = isEditing
        }
    }

    suspend fun changeRunningTime(time: Long, interval: Int, isEditing: Boolean = false) {
        dataStore.edit { pref ->
            pref[PreferenceKeys.RUNNING_TIME] = time
            pref[PreferenceKeys.RUNNING_INTERVAL] = interval
            pref[PreferenceKeys.RUNNING_IS_EDITING] = isEditing
        }
    }

    suspend fun editRunningtime(isEditing: Boolean = false) {
        dataStore.edit { pref ->
            pref[PreferenceKeys.RUNNING_IS_EDITING] = isEditing
        }
    }


    suspend fun changeStaticBikeTime(time: Long, interval: Int, isEditing: Boolean = false) {
        dataStore.edit { pref ->
            pref[PreferenceKeys.STATIC_BIKE_TIME] = time
            pref[PreferenceKeys.STATIC_BIKE_INTERVAL] = interval
            pref[PreferenceKeys.STATIC_BIKE_IS_EDITING] = isEditing
        }
    }

    suspend fun editStaticBikeTime(isEditing: Boolean = false) {
        dataStore.edit { pref ->
            pref[PreferenceKeys.STATIC_BIKE_IS_EDITING] = isEditing
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