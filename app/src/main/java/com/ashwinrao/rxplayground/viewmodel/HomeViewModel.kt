package com.ashwinrao.rxplayground.viewmodel

import androidx.lifecycle.ViewModel
import com.ashwinrao.rxplayground.data.Repository
import com.ashwinrao.rxplayground.data.model.TypicodePost
import io.reactivex.Observable

class HomeViewModel(private val repo: Repository) : ViewModel() {

    fun fetchPosts(limit: Int? = null): Observable<List<TypicodePost>> =
        repo.getPosts(limit)
}