package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.domain.repository.feature.timebased.ITimeFeatureRepository

interface ISleepRepository : ITimeFeatureRepository<SleepEntity>