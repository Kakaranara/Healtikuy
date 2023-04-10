package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.data.repository.UserRepository
import com.kocci.healtikuy.core.domain.usecase.StoreUseCase
import com.kocci.healtikuy.core.util.store.CharacterInStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreInteractor @Inject constructor(
    private val userRepository: UserRepository
) : StoreUseCase {
    override fun buyAvatar(characterInStore: CharacterInStore): Flow<Async<Unit>> {
        return userRepository.buyAvatar(characterInStore)
    }

    override fun isCoinEnough(coin: Int, characterInStore: CharacterInStore): Boolean {
        return coin >= characterInStore.price
    }
}