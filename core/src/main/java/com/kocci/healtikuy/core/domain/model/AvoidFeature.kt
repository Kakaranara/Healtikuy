package com.kocci.healtikuy.core.domain.model

data class AvoidFeature(
    val id : Int = 0,
    var alcohol: Boolean = false,
    var smoke: Boolean = false,
    var limitSugar : Boolean = false,
    var limitFat : Boolean = false,
    var timeStamp: Long = System.currentTimeMillis(),
)