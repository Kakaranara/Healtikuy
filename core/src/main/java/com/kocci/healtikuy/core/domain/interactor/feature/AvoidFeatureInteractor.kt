package com.kocci.healtikuy.core.domain.interactor.feature

import com.kocci.healtikuy.core.data.repository.feature.AvoidFeatureRepository
import com.kocci.healtikuy.core.domain.model.AvoidFeature
import com.kocci.healtikuy.core.domain.usecase.feature.AvoidFeatureUseCase
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.core.util.mapper.toDomain
import com.kocci.healtikuy.core.util.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class AvoidFeatureInteractor @Inject constructor(
    private val repository: AvoidFeatureRepository
) : AvoidFeatureUseCase {
    override fun getData(): Flow<AvoidFeature> {
        return repository.getData().map {
            if (it == null) {
                val data = AvoidFeature()
                repository.insert(data.toEntity())
                data
            } else if (!DateHelper.isToday(it.timeStamp)) {
                val data = AvoidFeature()
                repository.insert(data.toEntity())
                data
            } else {
                it.toDomain()
            }
        }
    }

    override fun isCheckable(): Boolean {
        // only available in > 9 PM.
        val checkAbleTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 21)
            set(Calendar.MINUTE, 0)
        }.time
        return Date(System.currentTimeMillis()).after(checkAbleTime)
    }

    override fun neverDoneItListener(): Flow<Boolean> {
        return repository.isAvoidChecked()
    }

    override suspend fun changeNeverDone(state: Boolean) {
        return repository.changeCheckedState(state)
    }

    override suspend fun checkAllForToday(obj: AvoidFeature) {
        obj.isTodayAllChecked = true
        repository.update(obj.toEntity())
    }

    override suspend fun imNotDrinkAlcohol(obj: AvoidFeature) {
        obj.alcohol = true
        repository.update(obj.toEntity())
    }

    override suspend fun imNotSmoking(obj: AvoidFeature) {
        obj.smoke = true
        repository.update(obj.toEntity())
    }

    override suspend fun iLimitMySugar(obj: AvoidFeature) {
        obj.limitSugar = true
        repository.update(obj.toEntity())
    }

    override suspend fun iLimitMyOil(obj: AvoidFeature) {
        obj.limitFat = true
        repository.update(obj.toEntity())
    }
}