package kr.hs.dgsw.mentomenv2.domain.usecase.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.mentomenv2.domain.exception.TokenException
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import retrofit2.HttpException
import java.io.IOException

abstract class NoParamUseCase<R> {

    abstract operator fun invoke(): Flow<NetworkResult<R>>

    protected fun <R> Flow<NetworkResult<R>>.execute(): Flow<NetworkResult<R>> = this.map { result ->
        when (result) {
            is NetworkResult.Success -> NetworkResult.Success(result.data!!)
            is NetworkResult.Error -> NetworkResult.Error(result.message)
            is NetworkResult.Loading -> NetworkResult.Loading()
        }
    }
}