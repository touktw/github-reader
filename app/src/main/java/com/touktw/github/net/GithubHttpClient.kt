package com.touktw.github.net

import com.touktw.github.base.BaseHttpClient
import okhttp3.Headers

/**
 * Created by taekim on 2020-03-13
 */

class GithubHttpClient(val authKey: String = AUTH_KEY) : BaseHttpClient() {

    override fun getHeaders(): Headers {
        return Headers.Builder()
            .add(KEY_AUTH, "token $authKey")
            .build()
    }

    companion object {
        const val KEY_AUTH = "Authorization"
        const val AUTH_KEY = "06917654abb58620afff4ea1f7877eabdf062823"
    }
}