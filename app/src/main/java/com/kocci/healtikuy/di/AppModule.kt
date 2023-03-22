package com.kocci.healtikuy.di

import com.kocci.healtikuy.core.domain.interactor.PreferencesInteractor
import com.kocci.healtikuy.core.domain.usecase.PreferencesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPreferencesUseCase(preferencesInteractor: PreferencesInteractor): PreferencesUseCase
}