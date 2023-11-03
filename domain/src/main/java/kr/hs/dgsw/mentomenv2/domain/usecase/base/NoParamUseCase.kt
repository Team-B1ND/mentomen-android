package kr.hs.dgsw.mentomenv2.domain.usecase.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.mentomenv2.domain.exception.TokenException
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult
import kr.hs.dgsw.mentomenv2.domain.util.Util
import retrofit2.HttpException
import java.io.IOException

abstract class NoParamUseCase<R> {

    abstract operator fun invoke(): Flow<NetworkResult<R>>

    fun execute(action: suspend () -> Flow<R>): Flow<NetworkResult<R>> = flow {
        try {
            emit(NetworkResult.Loading<R>())
            action().onEach {
                emit(NetworkResult.Success<R>(it))
            }
        } catch (e: HttpException) {
            if (e.code() == 401) emit(NetworkResult.Error<R>(Util.TOKEN_EXCEPTION))
            else emit(NetworkResult.Error<R>(Util.convertErrorBody(e)))
        } catch (e: IOException) {
            emit(NetworkResult.Error<R>(Util.NETWORK_ERROR_MESSAGE))
        } catch (e: TokenException) {
            emit(NetworkResult.Error<R>(Util.TOKEN_EXCEPTION))
        } catch (e: Exception) {
            emit(NetworkResult.Error<R>(Util.EXCEPTION))
        }
    }
}
