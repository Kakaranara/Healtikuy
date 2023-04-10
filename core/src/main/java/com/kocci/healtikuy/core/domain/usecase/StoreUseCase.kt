package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.util.store.CharacterInStore

interface StoreUseCase {
    fun isCoinEnough(coin: Int, characterInStore: CharacterInStore) : Boolean
    suspend fun buyAvatar(characterInStore: CharacterInStore)
}