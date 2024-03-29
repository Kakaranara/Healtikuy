package com.kocci.healtikuy.di

import com.kocci.healtikuy.core.domain.interactor.*
import com.kocci.healtikuy.core.domain.interactor.feature.AvoidFeatureInteractor
import com.kocci.healtikuy.core.domain.interactor.feature.NutritionInteractor
import com.kocci.healtikuy.core.domain.interactor.feature.SleepInteractor
import com.kocci.healtikuy.core.domain.interactor.feature.SunExposureInteractor
import com.kocci.healtikuy.core.domain.interactor.feature.WaterIntakeInteractor
import com.kocci.healtikuy.core.domain.interactor.feature.exercise.JoggingInteractor
import com.kocci.healtikuy.core.domain.interactor.feature.exercise.RunningInteractor
import com.kocci.healtikuy.core.domain.interactor.feature.exercise.StaticBikeInteractor
import com.kocci.healtikuy.core.domain.usecase.*
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.cardio.JoggingUseCase
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.cardio.RunningUseCase
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.cardio.StaticBikeUseCase
import com.kocci.healtikuy.core.domain.usecase.ChallengesUseCase
import com.kocci.healtikuy.core.domain.usecase.LeaderboardsUseCase
import com.kocci.healtikuy.core.domain.usecase.feature.AvoidFeatureUseCase
import com.kocci.healtikuy.core.domain.usecase.feature.NutritionUseCase
import com.kocci.healtikuy.core.domain.usecase.feature.SleepUseCase
import com.kocci.healtikuy.core.domain.usecase.feature.SunExposureUseCase
import com.kocci.healtikuy.core.domain.usecase.feature.WaterIntakeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    /**
     * This class are generated by hilt
     * For binding purpose purpose.
     * Hilt automatically generate the "Use Case" for us.
     */

    @Binds
    @ViewModelScoped
    abstract fun bindPreferencesUseCase(preferencesInteractor: UserInteractor): UserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindWaterIntakeUseCase(waterIntakeInteractor: WaterIntakeInteractor): WaterIntakeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSleepUseCase(sleepInteractor: SleepInteractor): SleepUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindAuthUseCase(authInteractor: AuthInteractor): AuthUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindStoreUseCase(storeUseCase: StoreInteractor): StoreUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindJoggingUseCase(joggingUseCase: JoggingInteractor): JoggingUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindRunningUseCase(runningInteractor: RunningInteractor): RunningUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindStaticBikeUseCase(staticBikeInteractor: StaticBikeInteractor): StaticBikeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSyncUseCase(syncInteractor: SynchronizationInteractor): SynchronizationUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindLeaderboardsUseCase(leaderboardsInteractor: LeaderboardsInteractor): LeaderboardsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindChallengesUseCase(interactor: ChallengesInteractor): ChallengesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindNutritionUseCase(interactor: NutritionInteractor): NutritionUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSunExposureUseCase(interactor: SunExposureInteractor): SunExposureUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindAvoidUseCase(inter : AvoidFeatureInteractor) : AvoidFeatureUseCase
}