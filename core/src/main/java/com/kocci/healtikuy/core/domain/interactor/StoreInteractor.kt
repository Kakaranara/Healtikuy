package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.repository.UserRepository
import com.kocci.healtikuy.core.domain.usecase.StoreUseCase
import com.kocci.healtikuy.core.util.store.CharacterInStore
import javax.inject.Inject

class StoreInteractor @Inject constructor(
    private val userRepository: UserRepository
) : StoreUseCase {
    override suspend fun buyAvatar(characterInStore: CharacterInStore) {

    }

    override fun isCoinEnough(coin: Int, characterInStore: CharacterInStore): Boolean {
        return coin >= characterInStore.price
    }
}