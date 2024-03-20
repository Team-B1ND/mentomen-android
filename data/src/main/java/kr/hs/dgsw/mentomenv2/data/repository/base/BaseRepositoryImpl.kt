package kr.hs.dgsw.mentomenv2.data.repository.base

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.mentomenv2.domain.exception.MenToMenException
import kr.hs.dgsw.mentomenv2.domain.util.Result
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import java.io.IOException
import java.net.UnknownHostException

abstract class BaseRepositoryImpl {
    protected fun <R> execute(action: suspend () -> Flow<R>): Flow<Result<R>> =
        flow {
            try {
                emit(Result.Loading())
                action().onEach { data ->
                    emit(Result.Success(data))
                }.catch { e ->
                    when (e) {
                        is retrofit2.HttpException -> {
                            if (e.code() == 401 || e.code() == 400 || e.code() == 403) {
                                emit(Result.Error(Utils.TOKEN_EXCEPTION))
                            } else {
                                emit(Result.Error(Utils.convertErrorBody(e)))
                            }
                        }

                        is UnknownHostException ->  {
                            Log.e("execute: ", "unKnownHostException")
                            emit(Result.Error(Utils.NETWORK_ERROR_MESSAGE))
                        }

                        is MenToMenException -> {
                            emit(Result.Error(e.message))
                        }

                        is IOException -> emit(Result.Error(Utils.NETWORK_ERROR_MESSAGE))
                        else -> emit(Result.Error(Utils.EXCEPTION))
                    }
                }.collect()
            } catch (e: Exception) {
                Log.e("BaseRepository", "FlowError : $e")
            }
        }
}
