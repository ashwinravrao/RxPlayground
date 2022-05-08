package com.ashwinrao.rxplayground.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashwinrao.rxplayground.data.Repository

class ViewModelFactory(private val repo: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repo) as T
            else -> throw IllegalArgumentException("$modelClass could not be created")
        }
    }
}