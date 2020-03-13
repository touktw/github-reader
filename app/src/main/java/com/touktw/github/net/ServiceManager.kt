package com.touktw.github.net

import com.touktw.github.base.BaseHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by taekim on 2020-03-13
 */


object ServiceManager {
    fun <T> getService(client: BaseHttpClient, service: Class<out T>): T {
        return Retrofit.Builder()
                .baseUrl(client.getBaseUrl())
                .client(client.getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(service)
    }
}