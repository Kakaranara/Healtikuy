package com.kocci.healtikuy.core.domain.usecase.exercise

import com.kocci.healtikuy.core.constant.CardioType

interface CardioListUseCase {
    fun getCardioStatus() : List<CardioType>

}