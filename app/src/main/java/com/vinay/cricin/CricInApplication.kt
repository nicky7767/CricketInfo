package com.vinay.cricin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CricInApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}