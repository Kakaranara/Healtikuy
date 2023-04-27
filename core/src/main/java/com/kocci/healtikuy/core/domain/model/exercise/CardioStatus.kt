package com.kocci.healtikuy.core.domain.model.exercise

import com.kocci.healtikuy.core.constant.CardioType

data class CardioStatus(
    val name: CardioType,
    var isCompleted : Boolean = false
)
