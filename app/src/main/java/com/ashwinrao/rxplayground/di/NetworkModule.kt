package com.ashwinrao.rxplayground.di

import android.app.Application
import com.ashwinrao.rxplayground.data.BASE_URL
import com.ashwinrao.rxplayground.data.ConnectivityInterceptor
import com.ashwinrao.rxplayground.data.Repository
import com.ashwinrao.rxplayground.data.TypicodeService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val application: Application) {

    @Singleton
    @Provides
    fun providesRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        connectivityInterceptor: ConnectivityInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(connectivityInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun providesConnectivityInterceptor(): ConnectivityInterceptor =
        ConnectivityInterceptor(application.applicationContext)

    @Singleton
    @Provides
    fun providesRepository(service: TypicodeService): Repository =
        Repository(service)

    @Singleton
    @Provides
    fun providesService(retrofit: Retrofit): TypicodeService =
        retrofit.create(TypicodeService::class.java)
}