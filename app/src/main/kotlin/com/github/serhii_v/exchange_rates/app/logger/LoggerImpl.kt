package com.github.serhii_v.exchange_rates.app.logger

import com.github.serhii_v.exchange_rates.BuildConfig
import com.github.serhii_v.exchange_rates.domain.common.logger.Logger
import org.jetbrains.annotations.NonNls
import timber.log.Timber
import javax.inject.Inject


class LoggerImpl @Inject constructor() : Logger {

    init {
        if (BuildConfig.DEBUG) {
            Timber.plant(PrefixDebugTree())
        }
        i("init, BuildConfig.DEBUG= ${BuildConfig.DEBUG}")
    }

    // implements Logger -------------------------------------------------------
    override val isDebug: Boolean
        get() = BuildConfig.DEBUG



    override fun v(msg: String, vararg values: Any?) {
        Timber.v(msg, values)
    }

    override fun v(t: Throwable) {
        Timber.v(t)
    }

    override fun v(t: Throwable, message: String, vararg args: Any) {
        Timber.v(t, message, args)
    }



    override fun d(@NonNls msg: String, vararg values: Any?) {
        Timber.d(msg, values)
    }

    override fun d(t: Throwable) {
        Timber.d(t)
    }

    override fun d(t: Throwable, message: String, vararg args: Any) {
        Timber.d(t, message, args)
    }



    override fun i(msg: String, vararg values: Any?) {
        Timber.i(msg, values)
    }

    override fun i(t: Throwable) {
        Timber.i(t)
    }

    override fun i(t: Throwable, message: String, vararg args: Any) {
        Timber.i(t, message, args)
    }



    override fun w(msg: String, vararg values: Any?) {
        Timber.w(msg, values)
    }

    override fun w(t: Throwable) {
        Timber.w(t)
    }

    override fun w(t: Throwable, message: String, vararg args: Any) {
        Timber.w(t, message, args)
    }



    override fun e(msg: String, vararg values: Any?) {
        Timber.e(msg, values)
    }

    override fun e(t: Throwable) {
        Timber.e(t)
    }

    override fun e(t: Throwable, message: String, vararg args: Any) {
        Timber.e(t, message, args)
    }

}