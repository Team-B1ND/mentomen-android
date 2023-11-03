package kr.hs.dgsw.mentomenv2.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult
import kr.hs.dgsw.mentomenv2.domain.util.Util
import kr.hs.dgsw.smartschool.dodamdodam.widget.Event
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _viewEvent = MutableLiveData<Event<Any>>()
    val viewEvent: LiveData<Event<Any>> = _viewEvent

    fun <T> Flow<NetworkResult<T>>.safeApiCall(
        isLoading: MutableLiveData<Boolean>,
        successAction: (T?) -> Unit,
        errorAction: (String?) -> Unit
    ) = onEach { resource ->
        Log.d("safeApiCall: ", "%%%%%%%%%%%%%%%%%%")
        when (resource) {
            is NetworkResult.Success -> {
                isLoading.value = false
                successAction.invoke(resource.data)
            }
            is NetworkResult.Loading -> {
                isLoading.value = true
            }
            is NetworkResult.Error -> {
                isLoading.value = false
                if (resource.message == Util.TOKEN_EXCEPTION) {
                    _error.value = resource.message ?: "세션이 만료되었습니다."
                } else {
                    errorAction.invoke(resource.message)
                }
            }
        }
    }
        .catch {
            Log.i("ERROR", "safeApiCall: ")
        }
}
