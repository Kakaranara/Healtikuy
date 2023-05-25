package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.challenges.Challenge
import com.kocci.healtikuy.core.domain.model.challenges.UserChallengeAttributes
import kotlinx.coroutines.flow.Flow

interface ChallengesUseCase {
    fun getChallengeProgress(): Flow<Async<List<Challenge>>>
    fun completeChallenge(documentId: String): Flow<Async<Unit>>
    suspend fun getUserAttributes() : UserChallengeAttributes
    suspend fun addCoins(coin: Int)
}