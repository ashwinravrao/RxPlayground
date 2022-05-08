package com.ashwinrao.rxplayground.di

import com.ashwinrao.rxplayground.ui.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModule::class])
interface AppComponent {
    fun inject(fragment: HomeFragment)
}