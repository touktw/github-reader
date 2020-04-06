package com.touktw.github.base

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

/**
 * Created by taekim on 2020-03-13
 */

abstract class BaseHttpClient {
    abstract fun getHeaders(): Headers?

    val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request()
                    val builder = original.newBuilder()
                    getHeaders()?.let {
                        builder.headers(it)
                    }
                    val request = builder
                        .method(original.method, original.body)
                        .build()

                    return chain.proceed(request)

                }

            })
            .build()
    }
}