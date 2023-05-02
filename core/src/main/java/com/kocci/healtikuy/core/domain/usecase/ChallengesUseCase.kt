package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.Challenge
import com.kocci.healtikuy.core.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface ChallengesUseCase {
    fun getChallengeProgress(): Flow<Async<List<Challenge>>>
    fun completeChallenge(documentId: String): Flow<Async<Unit>>
    suspend fun getUserLocalData() : UserPreferences
    suspend fun addCoins(coin: Int)
}