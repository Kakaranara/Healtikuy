package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.local.entity.ChallengeEntity
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.Challenge
import kotlinx.coroutines.flow.Flow

/**
 * This repo is the other feature such as challenges, daily login, etc..
 * we can say as, bonus.
 */
interface IHealtikuyRepository {
    fun getChallenges(): Flow<Async<List<Challenge>>>
    suspend fun updateChallenges(entity: Challenge)
}