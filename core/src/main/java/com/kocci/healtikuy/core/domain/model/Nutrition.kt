package com.kocci.healtikuy.core.domain.model

import com.kocci.healtikuy.core.util.helper.DateHelper

data class Nutrition(
    val foodName: String,
    val unixTimestamp: Long = DateHelper.getUnixEpoch()
)