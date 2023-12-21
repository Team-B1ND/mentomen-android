package kr.hs.dgsw.mentomenv2.data.repository.base

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.mentomenv2.domain.exception.TokenException
import kr.hs.dgsw.mentomenv2.domain.util.Result
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import java.io.IOException

abstract class BaseRepositoryImpl {
    protected fun <R> execute(action: suspend () -> Flow<R>): Flow<Result<R>> = flow {
//        try {
            Log.d("BaseReppsitory", "call Loading")
            emit(Result.Loading())
            action().onEach { data ->
                Log.d("BaseReppsitory", "call action.onEach Success")
                emit(Result.Success(data))
            }.collect()
//            }.catch { e ->
//                Log.d("BaseReppsitory", "onError: e.name: $e message: ${e.message}")
//                when (e) {
//                    is retrofit2.HttpException -> {
//                        if (e.code() == 401) emit(Result.Error(Utils.TOKEN_EXCEPTION))
//                        else emit(Result.Error(Utils.convertErrorBody(e)))
//                    }
//
//                    is IOException -> emit(Result.Error(Utils.NETWORK_ERROR_MESSAGE))
//                    is TokenException -> emit(Result.Error(Utils.TOKEN_EXCEPTION))
//                    else -> emit(Result.Error(Utils.EXCEPTION))
//                }
//            }.collect()
//        } catch (e: Exception) {
//            Log.d("BaseReppsitory", "FlowError : $e")
//            emit(Result.Error(Utils.EXCEPTION))
//        }
    }
}
