package kr.hs.dgsw.mentomenv2.domain.usecase.base

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult

abstract class UseCase<PR, R> {
    abstract operator fun invoke(params: PR): Flow<NetworkResult<R>>
}
