package kr.hs.dgsw.mentomenv2.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kr.hs.dgsw.smartschool.dodamdodam.widget.Event
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel
    @Inject
    constructor() : ViewModel() {
        private val _error = MutableSharedFlow<String>()
        val error = _error.asSharedFlow()

        private val _viewEvent = MutableSharedFlow<Event<Any>>()
        val viewEvent = _viewEvent.asSharedFlow()

        fun viewEvent(content: Any) {
            viewModelScope.launch {
                _viewEvent.emit(Event(content))
            }
        }

        fun <T> Flow<Result<T>>.safeApiCall(
            isLoading: MutableStateFlow<Boolean>? = null,
            successAction: (T?) -> Unit,
            errorAction: (String?) -> Unit = {},
            isEmitError: Boolean = true,
        ) = onEach { resource ->

            when (resource) {
                is Result.Success -> {
                    isLoading?.value = false
                    successAction(resource.data)
                }

                is Result.Loading -> {
                    isLoading?.value = true
                }

                is Result.Error -> {
                    isLoading?.value = false
                    errorAction(resource.message)

                    if (isEmitError) {
                        viewModelScope.launch {
                            Log.e("baseViewModel", "message: ${resource.message}")
                            _error.emit(resource.message.toString())
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
