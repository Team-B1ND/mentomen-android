package kr.hs.dgsw.mentomenv2.data.datasource

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.response.GetCodeResponse

interface DAuthDataSource {
    fun getCode(
        id: String,
        pw: String,
        clientId: String,
        redirectURL: String,
    ): Flow<GetCodeResponse>
}
