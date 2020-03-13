package com.touktw.github.model

import com.google.gson.annotations.SerializedName

/**
 * Created by taekim on 2020-03-13
 */

data class UserModel(
    val url: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    val name: String?,
    val email: String?
) : BaseModel()
