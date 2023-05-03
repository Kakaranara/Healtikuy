package com.kocci.healtikuy.core.domain.usecase.feature.exercise.scheduler

sealed class ExerciseTimeIndicator(val time: Long, val interval: Int) {
    class Set(time: Long, interval: Int) : ExerciseTimeIndicator(time, interval)
    object NotSet : ExerciseTimeIndicator(0, 0)
}