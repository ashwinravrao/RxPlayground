package com.ashwinrao.rxplayground

import android.app.Application
import com.ashwinrao.rxplayground.di.AppComponent
import com.ashwinrao.rxplayground.di.DaggerAppComponent
import com.ashwinrao.rxplayground.di.NetworkModule

class RxPlayground : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .networkModule(NetworkModule(this))
            .build()
    }

}