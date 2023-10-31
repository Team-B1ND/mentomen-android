package kr.hs.dgsw.mentomenv2.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    fun <T> BaseViewModel.safeApiCall(
        apiCall: suspend () -> T
    ): Flow<Result<T>> = flow {
        try {
            val result = kotlin.runCatching {
                apiCall()
            }
            result.onSuccess { data ->
                emit(Result.success(data))
            }
            result.onFailure { exception ->
                emit(Result.failure(exception))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
