package com.kocci.healtikuy.core.data.repository.feature

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.AvoidFeatureEntity
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import javax.inject.Inject

class AvoidFeatureRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val preference: UserPreferencesManager
) {
    fun getData() = localDataSource.getAvoidData()
    fun isAvoidChecked() = preference.isAvoidChecked
    suspend fun insert(entity: AvoidFeatureEntity) = localDataSource.insertAvoidData(entity)
    suspend fun update(entity: AvoidFeatureEntity) = localDataSource.updateAvoidData(entity)
    suspend fun changeCheckedState(state: Boolean) = preference.changeAvoidCheckedState(state)
}