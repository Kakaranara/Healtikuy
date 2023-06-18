package com.kocci.healtikuy.core.util.mapper

import com.kocci.healtikuy.core.data.local.entity.AvoidFeatureEntity
import com.kocci.healtikuy.core.domain.model.AvoidFeature

fun AvoidFeature.toEntity(): AvoidFeatureEntity {
    return AvoidFeatureEntity(
        id = this.id,
        alcohol = this.alcohol,
        smoke = this.smoke,
        limitSugar = this.limitSugar,
        limitFat = this.limitFat,
        timeStamp = this.timeStamp,
        isTodayAllChecked = this.isTodayAllChecked
    )
}

fun AvoidFeatureEntity.toDomain(): AvoidFeature {
    return AvoidFeature(
        id = this.id,
        alcohol = this.alcohol,
        smoke = this.smoke,
        limitSugar = this.limitSugar,
        limitFat = this.limitFat,
        timeStamp = this.timeStamp,
        isTodayAllChecked = this.isTodayAllChecked

    )
}