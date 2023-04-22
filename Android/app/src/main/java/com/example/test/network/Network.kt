package com.example.test.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://00672285.us-south.apigw.appdomain.cloud/demo-gapsi/"
private const val HEADER = "X-IBM-Client-Id"
private const val API_KEY = "adb8204d-d574-4394-8c1a-53226a40876e"

object Network {
    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).addInterceptor {
            try {
                it.proceed(
                    it.request().newBuilder()
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .addHeader(HEADER, API_KEY).build()
                )
            } catch (e: Exception) {
                val offlineRequest = it.request().newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
                it.proceed(offlineRequest)
            }
        }.build()
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val service: Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .run {
            create(Api::class.java)
        }
}