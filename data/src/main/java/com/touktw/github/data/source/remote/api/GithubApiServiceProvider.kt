package com.touktw.github.data.source.remote.api

import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.touktw.github.data.BuildConfig
import kotlinx.coroutines.CoroutineScope
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

object GithubApiServiceProvider {
    private const val DEFAULT_SOCKET_TIMEOUT_SEC = 60L
    private const val BASE_URL = "https://api.github.com"

    private var apiService: GithubApiService = createService()
    private lateinit var retrofit: Retrofit


    @Synchronized
    fun getService(): GithubApiService {
        return apiService
    }

    private fun createService(): GithubApiService {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(GithubApiService::class.java)
    }


    private fun createOkHttpClient(
        connectTimeoutSec: Long = DEFAULT_SOCKET_TIMEOUT_SEC,
        readTimeoutSec: Long = DEFAULT_SOCKET_TIMEOUT_SEC,
        writeTimeoutSec: Long = DEFAULT_SOCKET_TIMEOUT_SEC
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor {}.apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
            })
            .connectTimeout(connectTimeoutSec, TimeUnit.SECONDS)
            .readTimeout(readTimeoutSec, TimeUnit.SECONDS)
            .writeTimeout(writeTimeoutSec, TimeUnit.SECONDS)
            .build()
    }


    private class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val newBuilder = chain.request().newBuilder()
            header.forEach { entry ->
                newBuilder.addHeader(entry.key, entry.value)
            }
            return chain.proceed(newBuilder.build())
        }
    }

    private class NullOnEmptyConverterFactory : Converter.Factory() {

        override fun responseBodyConverter(
            type: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *>? {
            val delegate = retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
            return Converter<ResponseBody, Any> { body ->
                if (body.contentLength() == 0L) Any() else delegate.convert(
                    body
                )
            }
        }
    }

    private var token: String = ""

    fun updateToken(token: String?) {
        this.token = token ?: ""
        header["Authorization"] = "token ${this.token}"
    }

    private val header = mutableMapOf(
        "Accept" to "application/json;charset=UTF-8",
        "Content-type" to "application/json;charset=UTF-8",
        "Authorization" to "token ${this.token}"
    )
}