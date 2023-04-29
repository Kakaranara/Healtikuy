package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.data.repository.HealtikuyRepository
import com.kocci.healtikuy.core.data.repository.UserRepository
import com.kocci.healtikuy.core.domain.model.Challenge
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.core.domain.usecase.home.ChallengesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChallengesInteractor @Inject constructor(
    private val healtikuyRepository: HealtikuyRepository,
    private val userRepository: UserRepository
) : ChallengesUseCase {
    override fun getChallengeProgress(): Flow<Async<List<Challenge>>> {
        return healtikuyRepository.getChallenges()
    }

    override fun completeChallenge(documentId: String): Flow<Async<Unit>> {
        return healtikuyRepository.completeChallenges(documentId)
    }

    override suspend fun getUserLocalData(): UserPreferences {
        return userRepository.getRawUserPreferences()
    }

    override suspend fun addCoins(coin: Int) {
        healtikuyRepository.addCoins(coin)
    }
}