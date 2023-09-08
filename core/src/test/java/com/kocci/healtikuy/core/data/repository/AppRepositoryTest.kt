package com.kocci.healtikuy.core.data.repository

import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.domain.model.UserPreferences
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AppRepositoryTest {

    @Mock
    private lateinit var remoteSource: RemoteDataSource

    @Mock
    private lateinit var pref: UserPreferencesManager

    private lateinit var repository: AppRepository

    @Before
    fun setup() {
        repository = AppRepository(remoteSource, pref)
    }

    //test later
    @Test
    fun `reducePointIfAbsent, if reduced points too large, should set point to 0`() {
        `when`(pref.userPreferences).thenReturn(flow { UserPreferences(0,0,0,"f","f","f", setOf<String>(), 0,0,0) })
    }
}
