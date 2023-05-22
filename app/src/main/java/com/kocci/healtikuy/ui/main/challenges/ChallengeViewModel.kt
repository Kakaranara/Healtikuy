package com.kocci.healtikuy.ui.main.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.usecase.ChallengesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(
    private val challengesUseCase: ChallengesUseCase
) : ViewModel() {
    val progress = challengesUseCase.getChallengeProgress().asLiveData()
    fun completeChallenges(id: String) = challengesUseCase.completeChallenge(id).asLiveData()
    suspend fun getUserAttributes() = challengesUseCase.getUserAttributes()
    fun addCoin(coin: Int) {
        viewModelScope.launch {
            challengesUseCase.addCoins(coin)
        }
    }
}