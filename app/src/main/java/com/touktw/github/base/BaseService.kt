package com.touktw.github.base

import com.touktw.github.model.RepositoryModel
import com.touktw.github.model.UserModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by taekim on 2020-03-13
 */


interface GithubService {

    @GET("user")
    fun getUser(): Call<UserModel>

    @GET("user/repos")
    fun getRepositories(): Call<List<RepositoryModel>>
}