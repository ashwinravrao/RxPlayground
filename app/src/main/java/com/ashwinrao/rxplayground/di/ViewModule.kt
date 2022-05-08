package com.ashwinrao.rxplayground.di

import androidx.lifecycle.ViewModelProvider
import com.ashwinrao.rxplayground.data.Repository
import com.ashwinrao.rxplayground.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModule {

    @Singleton
    @Provides
    fun providesViewModelFactory(repo: Repository) : ViewModelProvider.Factory =
        ViewModelFactory(repo)
}