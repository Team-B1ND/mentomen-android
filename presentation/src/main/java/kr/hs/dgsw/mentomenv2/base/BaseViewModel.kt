package kr.hs.dgsw.mentomenv2.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _tokenErrorEvent = MutableLiveData<String>()
    val tokenErrorEvent = _tokenErrorEvent

    fun <T> Flow<NetworkResult<T>>.safeApiCall(
        isLoading: MutableLiveData<Boolean>? = null,
        successAction: (T?) -> Unit,
        errorAction: (String?) -> Unit
    ) = onEach { resource ->

        when (resource) {
            is NetworkResult.Success -> {
                isLoading?.value = false
                successAction.invoke(resource.data)
            }
            is NetworkResult.Loading -> {
                isLoading?.value = true
            }
            is NetworkResult.Error -> {
                isLoading?.value = false
                if (resource.message == Utils.TOKEN_EXCEPTION) {
                    _tokenErrorEvent.value = resource.message ?: "세션이 만료되었습니다."
                } else {
                    errorAction.invoke(resource.message)
                }
            }
        }
    }
}
