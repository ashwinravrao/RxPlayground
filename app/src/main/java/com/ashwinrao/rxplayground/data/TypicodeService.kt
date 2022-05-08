package com.ashwinrao.rxplayground.data

import com.ashwinrao.rxplayground.data.model.TypicodePost
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface TypicodeService {

    @GET("posts")
    fun getPosts(): Observable<List<TypicodePost>>

    @GET("posts/{limit}")
    fun getPosts(@Path("limit") limit: Int): Observable<List<TypicodePost>>

}