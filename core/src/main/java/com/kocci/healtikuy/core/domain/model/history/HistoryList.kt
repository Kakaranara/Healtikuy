package com.kocci.healtikuy.core.domain.model.history

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryList(
    val groupList: List<String>,
    val itemList: HashMap<String, List<String>>
) : Parcelable