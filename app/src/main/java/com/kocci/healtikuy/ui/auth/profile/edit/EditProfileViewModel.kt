package com.kocci.healtikuy.ui.auth.profile.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private var _avatar : MutableLiveData<String> = MutableLiveData()
    val avatar : LiveData<String> get()= _avatar

    fun changeAvatar(avatar: String){
        _avatar.value = avatar
    }


    fun updateProfile(userData: UserPreferences) = userUseCase.updateProfile(userData).asLiveData()
}