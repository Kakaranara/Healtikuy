package com.kocci.healtikuy.core.domain.usecase.feature

import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.domain.usecase.TimeBasedFeatureUseCase


interface SleepUseCase : TimeBasedFeatureUseCase<Sleep> {
    fun getSleepTips(): List<String>
}