package com.kocci.healtikuy.core.domain.usecase.exercise.cardio

import com.kocci.healtikuy.core.domain.model.exercise.StaticBike
import com.kocci.healtikuy.core.domain.usecase.exercise.ExerciseUseCase
import com.kocci.healtikuy.core.domain.usecase.exercise.scheduler.ExerciseSchedulerUseCase

interface StaticBikeUseCase : ExerciseUseCase<StaticBike>, ExerciseSchedulerUseCase {}