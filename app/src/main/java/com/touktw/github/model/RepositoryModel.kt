package com.touktw.github.model

/**
 * Created by taekim on 2020-03-13
 */

data class OwnerModel(
    val login: String?
)

data class RepositoryModel(
    val id: Long?,
    val name: String?,
    val description: String?,
    val owner: OwnerModel?
)