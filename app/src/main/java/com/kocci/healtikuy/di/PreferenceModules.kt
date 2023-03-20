package com.kocci.healtikuy.di

import com.kocci.healtikuy.core.domain.PreferencesInteractor
import com.kocci.healtikuy.core.domain.PreferencesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class PreferenceModules {

    @Binds
    @ViewModelScoped
    abstract fun bindPreferenceUseCase(preferencesInteractor: PreferencesInteractor): PreferencesUseCase
}