package com.iambenbradley.chefbook.dagger

import com.iambenbradley.chefbook.retrofit.ApiInterface
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetModule(val baseUrl: String) {

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun moshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        baseUrl(baseUrl)
        addConverterFactory(MoshiConverterFactory.create(moshi))
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }.build()

    @Provides
    @Singleton
    fun apiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)
}