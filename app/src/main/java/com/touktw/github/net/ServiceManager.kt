package com.touktw.github.net

import com.touktw.github.base.BaseApi
import com.touktw.github.base.BaseHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by taekim on 2020-03-13
 */


object ServiceManager {
    private val services = mutableMapOf<String, BaseApi>()
    fun <T : BaseApi> getService(
        client: BaseHttpClient,
        baseUrl: String,
        service: Class<out T>
    ): T {
        return services[service.simpleName] as T? ?: Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client.client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(service).also {
                services[service.simpleName] = it
            }
    }
}