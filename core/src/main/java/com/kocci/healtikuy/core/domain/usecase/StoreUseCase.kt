package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.util.helper.CharacterInStore
import kotlinx.coroutines.flow.Flow

interface StoreUseCase {
    fun isCoinEnough(coin: Int, characterInStore: CharacterInStore) : Boolean
    fun buyAvatar(characterInStore: CharacterInStore) : Flow<Async<Unit>>
}