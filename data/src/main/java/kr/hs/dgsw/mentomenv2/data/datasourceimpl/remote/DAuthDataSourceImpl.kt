package kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.mentomenv2.data.datasource.DAuthDataSource
import kr.hs.dgsw.mentomenv2.data.request.GetCodeRequest
import kr.hs.dgsw.mentomenv2.data.response.GetCodeResponse
import kr.hs.dgsw.mentomenv2.data.service.DAuthService
import kr.hs.dgsw.mentomenv2.domain.exception.WrongPasswordException
import retrofit2.HttpException
import javax.inject.Inject

class DAuthDataSourceImpl
    @Inject
    constructor(
        private val api: DAuthService,
    ) : DAuthDataSource {
        override fun getCode(
            id: String,
            pw: String,
            clientId: String,
            redirectURL: String,
        ): Flow<GetCodeResponse> {
            return flow {
                try {
                    emit(api.getCode(GetCodeRequest(id, pw, clientId, redirectURL)).data)
                } catch (e: HttpException) {
                    throw WrongPasswordException()
                }
            }
        }
    }
