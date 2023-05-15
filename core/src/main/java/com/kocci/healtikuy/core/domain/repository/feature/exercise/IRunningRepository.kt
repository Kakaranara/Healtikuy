package com.kocci.healtikuy.core.domain.repository.feature.exercise

import com.kocci.healtikuy.core.data.local.entity.exercise.RunningEntity

interface IRunningRepository : ICardioRepository<RunningEntity> {
    suspend fun changeLeaderboards(runningType : Int, time: Long)
}