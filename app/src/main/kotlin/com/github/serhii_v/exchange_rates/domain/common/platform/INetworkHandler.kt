package com.github.serhii_v.exchange_rates.domain.common.platform

interface INetworkHandler {
    fun isConnectedToInternet(): Boolean
}