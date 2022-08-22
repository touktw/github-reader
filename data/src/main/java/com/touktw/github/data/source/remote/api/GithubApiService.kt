package com.touktw.github.data.source.remote.api

import com.skydoves.sandwich.ApiResponse
import com.touktw.github.data.source.remote.dto.UserDto
import retrofit2.http.GET

interface GithubApiService {
    @GET("/user")
    suspend fun getUser(): ApiResponse<UserDto>
}