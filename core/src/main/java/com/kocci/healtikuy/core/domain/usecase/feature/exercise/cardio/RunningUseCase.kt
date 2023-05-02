package com.kocci.healtikuy.core.domain.usecase.feature.exercise.cardio

import com.kocci.healtikuy.core.domain.model.exercise.Running
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.ExerciseUseCase
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.scheduler.ExerciseSchedulerUseCase

interface RunningUseCase : ExerciseUseCase<Running>, ExerciseSchedulerUseCase {}