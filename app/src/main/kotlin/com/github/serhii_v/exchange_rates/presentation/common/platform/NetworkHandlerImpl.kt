package com.github.serhii_v.exchange_rates.presentation.common.platform

import android.content.Context
import com.github.serhii_v.exchange_rates.domain.common.platform.INetworkHandler
import com.github.serhii_v.exchange_rates.presentation.common.extension.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandlerImpl@Inject constructor(private val context: Context) : INetworkHandler {
    override fun isConnectedToInternet(): Boolean {
        return context.networkInfo?.isConnectedOrConnecting ?: false
    }
}