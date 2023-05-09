package com.kocci.healtikuy.core.domain.usecase.feature

import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.domain.usecase.TimeBasedFeatureUseCase
import com.kocci.healtikuy.core.util.helper.TipList


interface SleepUseCase : TimeBasedFeatureUseCase<Sleep> {
    fun getSleepTips(): TipList
}