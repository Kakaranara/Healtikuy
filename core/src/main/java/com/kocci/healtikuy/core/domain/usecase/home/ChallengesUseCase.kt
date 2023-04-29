package com.kocci.healtikuy.core.domain.usecase.home

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.Challenge
import kotlinx.coroutines.flow.Flow

interface ChallengesUseCase {
    fun getChallengeProgress(): Flow<Async<List<Challenge>>>
}