package com.kocci.healtikuy.ui.main.leaderboards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsAttr
import com.kocci.healtikuy.core.domain.usecase.LeaderboardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeaderboardsVM @Inject constructor(
    private val leaderboardsUseCase: LeaderboardsUseCase
) : ViewModel() {
    fun getData() = leaderboardsUseCase.getLeaderboardsData().asLiveData()
    fun getSortedData(list: List<LeaderboardsAttr>) = leaderboardsUseCase.sortByPoints(list)
}