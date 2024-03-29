package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.data.repository.HealtikuyRepository
import com.kocci.healtikuy.core.data.repository.UserRepository
import com.kocci.healtikuy.core.domain.model.challenges.Challenge
import com.kocci.healtikuy.core.domain.model.challenges.UserChallengeAttributes
import com.kocci.healtikuy.core.domain.usecase.ChallengesUseCase
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

    override suspend fun getUserAttributes(): UserChallengeAttributes {
        return userRepository.getUserChallengeAttributes()
    }

    override suspend fun addCoins(coin: Int) {
        healtikuyRepository.addCoins(coin)
    }
}