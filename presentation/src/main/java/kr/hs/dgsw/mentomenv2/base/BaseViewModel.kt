package kr.hs.dgsw.mentomenv2.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import kr.hs.dgsw.smartschool.dodamdodam.widget.Event
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _viewEvent = MutableLiveData<Event<Any>>()
    val viewEvent: LiveData<Event<Any>> = _viewEvent

    fun <T> Flow<T>.safeApiCall(
        isLoading: MutableLiveData<Boolean>? = null,
        successAction: (T?) -> Unit,
        errorAction: (String?) -> Unit
    ): Flow<T> {
        return onEach { resource ->
            successAction.invoke(resource)
            isLoading?.value = false
        }.onStart {
            isLoading?.value = true // 로딩 시작
        }.onCompletion { cause ->
            if (cause?.message == Utils.TOKEN_EXCEPTION) {
                _error.value = "세선이 만료되었습니다."
            } else if(cause != null) {
                errorAction.invoke(cause.message)
            }
            isLoading?.value = false // 로딩 종료
        }
    }
}
