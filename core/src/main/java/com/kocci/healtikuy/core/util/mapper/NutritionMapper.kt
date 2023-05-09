package com.kocci.healtikuy.core.util.mapper

import com.kocci.healtikuy.core.data.local.entity.NutritionEntity
import com.kocci.healtikuy.core.domain.model.Nutrition

fun NutritionEntity.toDomain(): Nutrition {
    return Nutrition(
        foodName = this.foodName,
        category = this.category,
        unixTimestamp = this.unixTimestamp
    )
}

fun Nutrition.toEntity(): NutritionEntity {
    return NutritionEntity(
        foodName = this.foodName,
        category = this.category,
        unixTimestamp = this.unixTimestamp
    )
}