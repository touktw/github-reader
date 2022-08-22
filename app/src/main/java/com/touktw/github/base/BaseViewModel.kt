package com.touktw.github.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.touktw.github.data.UserRepository
import com.touktw.github.util.Event
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

abstract class BaseViewModel : ViewModel() {
    protected val _uiEvent = MutableSharedFlow<Event<UiEvent>>()
    val uiEvent: SharedFlow<Event<UiEvent>> get() = _uiEvent.asSharedFlow()

    protected val exceptionHandler = CoroutineExceptionHandler { _, throwalbe ->
        viewModelScope.launch { _uiEvent.emit(Event(UiEvent.Toast(throwalbe.message))) }
    }

    protected fun fetchUseCase(useCaseBlock: suspend () -> Unit) {
        viewModelScope.launch(exceptionHandler) {
            useCaseBlock()
        }
    }
}

@HiltViewModel
class UserViewModel @Inject constructor(private val userUseCase: UserUseCase) : BaseViewModel() {
    fun fetchUser() {
        fetchUseCase { userUseCase() }
    }
}

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideUserUseCase(userRepository: UserRepository) = UserUseCase(userRepository)
}

class UserUseCase(
    private val userRepository: UserRepository
) : BaseUseCase {

    suspend operator fun invoke() {
        userRepository.getUser()
    }
}

interface BaseUseCase