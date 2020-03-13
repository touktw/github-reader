package com.touktw.github.net

import com.touktw.github.base.BaseApi
import com.touktw.github.model.RepositoryModel
import com.touktw.github.model.UserModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by taekim on 2020-03-13
 */


interface GithubApi : BaseApi {

    @GET("user")
    fun getUser(): Call<UserModel>

    @GET("user/repos")
    fun getRepositories(): Call<List<RepositoryModel>>
}