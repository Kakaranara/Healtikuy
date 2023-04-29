package com.kocci.healtikuy.ui.main.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kocci.healtikuy.core.domain.usecase.home.ChallengesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(
    private val challengesUseCase: ChallengesUseCase
) : ViewModel() {
    val progress = challengesUseCase.getChallengeProgress().asLiveData()
}