package com.github.serhii_v.exchange_rates.domain.common

sealed class Result<out TData, out TError> {

    data class Data<out TData>(val data: TData) : Result<TData, Nothing>()
    data class Error<out TError>(val error: TError) : Result<Nothing, TError>()

    val isError get() = this is Error<TError>
    val isSuccess get() = this is Data<TData>

    fun <TData> success(a: TData) = Data(a)
    fun <TError> error(b: TError) = Error(b)

    suspend fun either(onError: (TError) -> Unit,
                       onSuccess: suspend (TData) -> Unit): Result<TData, TError> {
        when (this) {
            is Data -> onSuccess(data)
            is Error -> onError(error)
        }
        return this
    }

}

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> Result<L, R>.flatMap(fn: (R) -> Result<L, T>): Result<L, T> =
    when (this) {
        is Result.Data -> Result.Data(data)
        is Result.Error -> fn(error)
    }

fun <T, L, R> Result<L, R>.map(fn: (R) -> (T)): Result<L, T> = this.flatMap(fn.c(::error))

