package kr.hs.dgsw.mentomenv2.domain.usecase.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.mentomenv2.domain.util.Result

abstract class NoParamUseCase<R> {

    abstract operator fun invoke(): Flow<Result<R>>

    protected fun <R> Flow<Result<R>>.execute(): Flow<Result<R>> =
        this.map { result ->
            when (result) {
                is Result.Success -> Result.Success(result.data!!)
                is Result.Error -> Result.Error(result.message)
                is Result.Loading -> Result.Loading()
            }
        }
}
