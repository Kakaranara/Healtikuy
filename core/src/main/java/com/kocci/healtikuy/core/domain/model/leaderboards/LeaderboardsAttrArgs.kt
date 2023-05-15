package com.kocci.healtikuy.core.domain.model.leaderboards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeaderboardsAttrArgs(
    val listData: List<LeaderboardsAttr>
) : Parcelable