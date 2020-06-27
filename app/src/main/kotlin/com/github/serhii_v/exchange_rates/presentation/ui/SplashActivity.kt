package com.github.serhii_v.exchange_rates.presentation.ui

import android.os.Bundle
import com.github.serhii_v.exchange_rates.presentation.common.navigation.Navigator
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.showMain(this)
        finish()
    }

}