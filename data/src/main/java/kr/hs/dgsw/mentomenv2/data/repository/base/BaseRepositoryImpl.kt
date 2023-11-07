package kr.hs.dgsw.mentomenv2.data.repository.base

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.mentomenv2.domain.exception.TokenException
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import java.io.IOException

abstract class BaseRepositoryImpl {
    protected fun <R> execute(action: suspend () -> Flow<R>): Flow<NetworkResult<R>> = flow {
        try {
            emit(NetworkResult.Loading())
            action().onEach { data ->
                Log.d("BaseReppsitory", "call action.onEach")
                emit(NetworkResult.Success(data))
            }.catch { e ->
                Log.d("BaseReppsitory", "onError: ")
                when(e) {
                    is retrofit2.HttpException -> {
                        if (e.code() == 401) emit(NetworkResult.Error(Utils.TOKEN_EXCEPTION))
                        else emit(NetworkResult.Error(Utils.convertErrorBody(e)))
                    }
                    is IOException -> emit(NetworkResult.Error(Utils.NETWORK_ERROR_MESSAGE))
                    is TokenException -> emit(NetworkResult.Error(Utils.TOKEN_EXCEPTION))
                    else -> emit(NetworkResult.Error(Utils.EXCEPTION))
                }
            }.collect()
        } catch (e: Exception) {
            Log.d("BaseReppsitory", "FlowError : $e")
            emit(NetworkResult.Error(Utils.EXCEPTION))
        }
    }
}
