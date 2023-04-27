package com.kocci.healtikuy.core.domain.usecase.exercise.cardio

import com.kocci.healtikuy.core.domain.model.exercise.Jogging
import com.kocci.healtikuy.core.domain.usecase.exercise.ExerciseUseCase
import com.kocci.healtikuy.core.domain.usecase.exercise.scheduler.ExerciseSchedulerUseCase

interface JoggingUseCase : ExerciseUseCase<Jogging>, ExerciseSchedulerUseCase