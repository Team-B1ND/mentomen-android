package com.b1nd.mentomen.data.repository.base

import com.b1nd.mentomen.domain.exception.MenToMenException
import com.b1nd.mentomen.domain.exception.WrongPasswordException
import com.b1nd.mentomen.domain.util.Log
import com.b1nd.mentomen.domain.util.Result
import com.b1nd.mentomen.domain.util.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
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
                    Log.e("BaseRepoImpl", "e: $e")
                    when (e) {
                        is retrofit2.HttpException -> {
                            if (e.code() == 401 || e.code() == 400 || e.code() == 403) {
                                emit(Result.Error(Utils.TOKEN_EXCEPTION))
                            } else {
                                emit(Result.Error(Utils.convertErrorBody(e)))
                            }
                        }

                        is UnknownHostException -> {
                            Log.e("execute: ", "unKnownHostException")
                            emit(Result.Error(Utils.NETWORK_ERROR_MESSAGE))
                        }

                        is MenToMenException -> {
                            Log.e("MenToMenException: ", "${e.message}")
                            emit(Result.Error(e.message))
                        }

                        is IOException -> emit(Result.Error(Utils.NETWORK_ERROR_MESSAGE))

                        is WrongPasswordException -> emit(Result.Error(Utils.WRONG_PASSWORD))

                        else -> {
                            Log.e("BaseRepository", e.stackTraceToString())
                            emit(Result.Error(Utils.EXCEPTION))
                        }
                    }
                }.collect()
            } catch (e: Exception) {
                Log.e("BaseRepository", "FlowError : ${e.stackTraceToString()}")
                throw e
            }
        }
}
