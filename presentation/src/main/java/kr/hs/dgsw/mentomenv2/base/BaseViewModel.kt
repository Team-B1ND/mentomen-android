package kr.hs.dgsw.mentomenv2.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.mentomenv2.domain.util.Result
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import kr.hs.dgsw.smartschool.dodamdodam.widget.Event
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel
    @Inject
    constructor() : ViewModel() {
        private val _error = MutableLiveData<String>()
        val error: LiveData<String> = _error

        private val _viewEvent = MutableLiveData<Event<Any>>()
        val viewEvent: LiveData<Event<Any>>
            get() = _viewEvent

        fun viewEvent(content: Any) {
            _viewEvent.value = Event(content)
        }

        fun <T> Flow<Result<T>>.safeApiCall(
            isLoading: MutableLiveData<Boolean>? = null,
            successAction: (T?) -> Unit,
            errorAction: (String?) -> Unit,
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
                    if (resource.message == Utils.TOKEN_EXCEPTION) {
                        _error.value = resource.message ?: "세션이 만료되었습니다."
                    } else {
                        errorAction.invoke(resource.message)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
