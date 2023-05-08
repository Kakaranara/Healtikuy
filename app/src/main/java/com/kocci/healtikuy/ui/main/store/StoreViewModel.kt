package com.kocci.healtikuy.ui.main.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kocci.healtikuy.core.domain.usecase.StoreUseCase
import com.kocci.healtikuy.core.domain.usecase.UserUseCase
import com.kocci.healtikuy.core.util.helper.CharacterInStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val storeUseCase: StoreUseCase,
    private val userUseCase: UserUseCase,
) : ViewModel() {
    val userData = userUseCase.getUserData().asLiveData()
    fun isCoinEnough(coin: Int, item: CharacterInStore) = storeUseCase.isCoinEnough(coin, item)
    fun buyAvatar(item: CharacterInStore) = storeUseCase.buyAvatar(item).asLiveData()

}