package com.github.serhii_v.exchange_rates.domain.common.logger

interface Logger {

    val isDebug: Boolean

    fun v(msg: String, vararg values: Any?)
    fun v(t: Throwable)
    fun v(t: Throwable, message: String, vararg args: Any)

    fun d(msg: String, vararg values: Any?)
    fun d(t: Throwable)
    fun d(t: Throwable, message: String, vararg args: Any)

    fun i(msg: String, vararg values: Any?)
    fun i(t: Throwable)
    fun i(t: Throwable, message: String, vararg args: Any)

    fun w(msg: String, vararg values: Any?)
    fun w(t: Throwable)
    fun w(t: Throwable, message: String, vararg args: Any)

    fun e(msg: String, vararg values: Any?)
    fun e(t: Throwable)
    fun e(t: Throwable, message: String, vararg args: Any)

}