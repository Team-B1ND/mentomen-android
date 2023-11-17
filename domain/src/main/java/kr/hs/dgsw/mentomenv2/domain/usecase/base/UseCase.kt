package kr.hs.dgsw.mentomenv2.domain.usecase.base

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.util.Result

abstract class UseCase<PR, R> {
    abstract operator fun invoke(params: PR): Flow<Result<R>>
}
