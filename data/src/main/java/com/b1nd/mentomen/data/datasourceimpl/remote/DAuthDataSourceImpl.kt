package com.b1nd.mentomen.data.datasourceimpl.remote

import com.b1nd.mentomen.domain.exception.WrongPasswordException
import com.b1nd.mentomen.data.datasource.DAuthDataSource
import com.b1nd.mentomen.data.request.GetCodeRequest
import com.b1nd.mentomen.data.response.GetCodeResponse
import com.b1nd.mentomen.data.service.DAuthService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
