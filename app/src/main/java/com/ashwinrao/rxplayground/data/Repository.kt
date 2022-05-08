package com.ashwinrao.rxplayground.data

import com.ashwinrao.rxplayground.data.model.TypicodePost
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository(private val service: TypicodeService) {

    fun getPosts(limit: Int?): Observable<List<TypicodePost>> =
        (limit?.let { service.getPosts(it) } ?: service.getPosts())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}