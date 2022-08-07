package com.myapps.coinksimpleregistersimulator.domain.utils

sealed class StateResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>() : StateResult<T>()
    class Success<T>(data: T) : StateResult<T>(data = data)
    class Failed<T>(message: String) : StateResult<T>(message = message)
}
