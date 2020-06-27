package com.github.serhii_v.exchange_rates.presentation.common.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


val Context.networkInfo: NetworkInfo? get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo


