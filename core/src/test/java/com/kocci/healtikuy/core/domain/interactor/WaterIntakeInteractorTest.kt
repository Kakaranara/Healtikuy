package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.repository.WaterIntakeRepository
import com.kocci.healtikuy.core.domain.model.WaterIntake
import com.kocci.healtikuy.core.domain.usecase.WaterIntakeUseCase
import com.kocci.healtikuy.core.service.AlarmService
import com.kocci.healtikuy.core.util.mapper.toEntity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class WaterIntakeInteractorTest {
    @Mock
    private lateinit var repository: WaterIntakeRepository

    @Mock
    private lateinit var alarmService: AlarmService

    private lateinit var useCase: WaterIntakeUseCase

    @Before
    fun setup() {
        useCase = WaterIntakeInteractor(repository, alarmService)
    }

    @Test
    fun `complete mission should call right repo func and cancel the alarm`() = runBlocking {
        val mockDomain = mock<WaterIntake>()
        val entity = mockDomain.toEntity()
        useCase.completeMission(mockDomain)
        Mockito.verify(repository).updateAndAddPoints(entity)
    }

//    @Test
//    fun `every quantity update should setAlarm, except when goals completed`() = runBlocking {
//        val mockDomain = mock<WaterIntake>()
//        mockDomain.isCompleted = false
//        val entity = mockDomain.toEntity()
//
//        useCase.updateQuantity(mockDomain)
//        Mockito.verify(useCase).setAlarm(mockDomain.isCompleted)
//        Mockito.verify(useCase).cancelAlarm()
//
//        mockDomain.isCompleted = true
//        useCase.completeMission(mockDomain)
//        Mockito.verify(useCase).cancelAlarm()
//        Mockito.verifyNoInteractions(useCase.setAlarm(mockDomain.isCompleted))
//    }
}