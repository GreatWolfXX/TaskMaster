package com.greatwolf.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

typealias RootError = Error

sealed interface Result<out D, out E : RootError> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E : RootError>(val error: E) : Result<Nothing, E>
    data object Loading : Result<Nothing, Nothing>
}

fun <D> Flow<D>.asResult(): Flow<Result<D, RootError>> =
    map<D, Result<D, RootError>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch { emit(Result.Error(it.toError())) }
