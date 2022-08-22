package com.touktw.github.data

import com.skydoves.sandwich.ApiResponse
import com.touktw.github.data.source.remote.api.GithubApiService
import com.touktw.github.data.source.remote.api.GithubApiServiceProvider
import com.touktw.github.data.source.remote.dto.UserDto
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface Repository {

}

interface UserRepository {
    suspend fun getUser(): UserDto
}

class UserRepositoryImpl @Inject constructor() : BaseRepositoryImpl(Dispatchers.IO),
    UserRepository {
    override suspend fun getUser(): UserDto {
        return call { getService().getUser() }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}


abstract class BaseRepositoryImpl(private val dispatcher: CoroutineDispatcher) {
    protected fun getService(): GithubApiService = GithubApiServiceProvider.getService()

    protected suspend fun <T> call(apiCall: suspend () -> ApiResponse<T>): T {
        return withContext(dispatcher) {
            when (val response = apiCall.invoke()) {
                is ApiResponse.Success -> response.data
                else -> throw IllegalArgumentException()
            }
        }
    }
}
