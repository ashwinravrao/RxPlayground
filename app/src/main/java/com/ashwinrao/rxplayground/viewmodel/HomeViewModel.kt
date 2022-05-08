package com.ashwinrao.rxplayground.viewmodel

import androidx.lifecycle.ViewModel
import com.ashwinrao.rxplayground.data.Repository
import com.ashwinrao.rxplayground.data.model.TypicodePost
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val repo: Repository) : ViewModel() {

    val posts: Observable<List<TypicodePost>> = fetchPosts()

    fun fetchPosts(limit: Int? = null): Observable<List<TypicodePost>> =
        repo.getPosts(limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}