package kr.hs.dgsw.mentomenv2.domain.usecase.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.mentomenv2.domain.util.Result

abstract class NoParamUseCase<R> {

    abstract operator fun invoke(): Flow<Result<R>>
}
