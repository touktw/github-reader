package com.touktw.github.net

/**
 * Created by taekim on 2020-03-13
 */

object GithubService {
    fun get(): GithubApi {
        val client = GithubHttpClient()
        return ServiceManager.getService(client, GithubApi::class.java)
    }
}