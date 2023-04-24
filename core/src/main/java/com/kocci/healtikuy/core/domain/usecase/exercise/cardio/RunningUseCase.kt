package com.kocci.healtikuy.core.domain.usecase.exercise.cardio

import com.kocci.healtikuy.core.domain.model.exercise.Running
import com.kocci.healtikuy.core.domain.usecase.exercise.ExerciseUseCase
import com.kocci.healtikuy.core.domain.usecase.exercise.scheduler.ExerciseSchedulerUseCase

interface RunningUseCase : ExerciseUseCase<Running>, ExerciseSchedulerUseCase{}