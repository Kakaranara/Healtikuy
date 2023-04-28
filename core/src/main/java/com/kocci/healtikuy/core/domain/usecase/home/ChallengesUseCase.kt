package com.kocci.healtikuy.core.domain.usecase.home

import com.kocci.healtikuy.core.domain.model.Challenges
import kotlinx.coroutines.flow.Flow

interface ChallengesUseCase {
    fun getChallengeProgress(): Flow<List<Challenges>>

}