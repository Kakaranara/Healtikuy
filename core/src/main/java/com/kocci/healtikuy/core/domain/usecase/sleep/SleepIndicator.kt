package com.kocci.healtikuy.core.domain.usecase.sleep

sealed class SleepIndicator(val sleepTime: Long) {
    class Set(data: Long) : SleepIndicator(data)
    object NotSet : SleepIndicator(0)
}