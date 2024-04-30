package kr.hs.dgsw.mentomenv2.base

import MutableEventFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.domain.util.Log
import kr.hs.dgsw.mentomenv2.domain.util.Result
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import kr.hs.dgsw.smartschool.dodamdodam.widget.Event
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel
    @Inject
    constructor() : ViewModel() {
        private val _error = MutableSharedFlow<String>()
        val error = _error.asSharedFlow()

        private val _viewEvent = MutableEventFlow<Event<Any>>()
        val viewEvent = _viewEvent.asEventFlow()

        fun viewEvent(content: Any) {
            viewModelScope.launch {
                _viewEvent.emit(Event(content))
            }
        }

        fun <T> Flow<Result<T>>.safeApiCall(
            isLoading: MutableStateFlow<Boolean>? = null,
            successAction: (T?) -> Unit,
            errorAction: (String?) -> Unit = {},
        ) = onEach { resource ->

            when (resource) {
                is Result.Success -> {
                    isLoading?.value = false
                    successAction.invoke(resource.data)
                }

                is Result.Loading -> {
                    isLoading?.value = true
                }

                is Result.Error -> {
                    isLoading?.value = false
                    Log.e("baseViewModel", "${resource.message}")
                    errorAction.invoke(resource.message)
                    when (resource.message) {
                        Utils.TOKEN_EXCEPTION -> {
                            viewModelScope.launch {
                                _error.emit(Utils.TOKEN_EXCEPTION)
                            }
                        }
                        Utils.NETWORK_ERROR_MESSAGE -> {
                            viewModelScope.launch {
                                _error.emit(Utils.NETWORK_ERROR_MESSAGE)
                            }
                        }
                        Utils.EXCEPTION -> {
                            viewModelScope.launch {
                                _error.emit(Utils.EXCEPTION)
                            }
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
