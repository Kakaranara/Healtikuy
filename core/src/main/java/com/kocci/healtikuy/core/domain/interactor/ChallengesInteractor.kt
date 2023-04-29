package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.data.repository.HealtikuyRepository
import com.kocci.healtikuy.core.domain.model.Challenge
import com.kocci.healtikuy.core.domain.usecase.home.ChallengesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChallengesInteractor @Inject constructor(
    private val healtikuyRepository: HealtikuyRepository
) : ChallengesUseCase {
    override fun getChallengeProgress(): Flow<Async<List<Challenge>>> {
        return healtikuyRepository.getChallenges()
    }
}