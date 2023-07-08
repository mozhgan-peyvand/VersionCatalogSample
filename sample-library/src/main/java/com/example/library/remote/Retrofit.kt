package com.example.library.remote

import com.example.library.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitHelper {

    private const val baseUrl = "https://quotable.io/"

    private val moshi: Moshi = Moshi.Builder().build()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().addNetworkInterceptor(
        loggingInterceptor
    )
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
        .build()

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .client(okHttpClient)
            .build()
    }
}