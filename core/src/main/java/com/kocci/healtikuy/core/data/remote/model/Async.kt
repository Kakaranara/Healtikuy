package com.kocci.healtikuy.core.data.remote.model

sealed class Async<out R> private constructor() {
    data class Success<out T>(val data: T) : Async<T>()
    data class Error(val msg: String) : Async<Nothing>()
    object Loading : Async<Nothing>()
}