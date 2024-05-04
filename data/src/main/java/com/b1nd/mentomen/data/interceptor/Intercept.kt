package com.b1nd.mentomen.data.interceptor

import com.b1nd.mentomen.data.repository.DataStoreRepositoryImpl
import com.b1nd.mentomen.domain.model.Token
import com.b1nd.mentomen.domain.util.Log
import com.b1nd.mentomen.domain.util.Result
import com.b1nd.mentomen.domain.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONException
import javax.inject.Inject

class Intercept
    @Inject
    constructor(
        private val dataStoreRepositoryImpl: DataStoreRepositoryImpl,
    ) : Interceptor {
        private val tokenHeader = "Authorization"

        private lateinit var token: Token
        private lateinit var response: Response

        @Synchronized
        override fun intercept(chain: Interceptor.Chain): Response {
            getToken()
            response = chain.proceedWithToken(chain.request())
            if (response.code == 401 || response.code == 403 || response.code == 400 || response.code == 500) {
                response.close()
                chain.makeTokenRefreshCall()
            }

            return response
        }

        private fun Interceptor.Chain.makeTokenRefreshCall() {
            fetchToken()
            response = this.proceedWithToken(this.request())

            if (response.code == 401 || response.code == 403 || response.code == 400 || response.code == 500) {
                // 만약 토큰 오류 발생 시 로그인
                try {
                    response.close()
                    response = login()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }

        private fun Interceptor.Chain.login(): Response {
            // request에 토큰을 붙여서 새로운 request 생성 -> 진행
            response = this.proceedWithToken(this.request())

            return if (response.code == 401 || response.code == 403 || response.code == 400 || response.code == 500) {
                Log.d("TokenTest", "Here is Login")
                clearDataStore()
                Response.Builder()
                    .request(this.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(401)
                    .message(Utils.TOKEN_EXCEPTION)
                    .body(Utils.TOKEN_EXCEPTION.toResponseBody(null))
                    .build()
            } else {
                response
            }
        }

        private fun getToken() =
            runBlocking(Dispatchers.IO) {
                dataStoreRepositoryImpl.getToken().let {
                    it.collect {
                        Log.d(
                            "getToken: ",
                            "token: ${it.data?.accessToken ?: ""}, ${it.data?.refreshToken ?: ""}",
                        )
                        token =
                            when (it) {
                                is Result.Success ->
                                    Token(
                                        it.data?.accessToken ?: "",
                                        it.data?.refreshToken ?: "",
                                    )

                                is Result.Error -> Token("", "")
                                is Result.Loading -> Token("", "")
                            }
                    }
                }
            }

        private fun clearDataStore() =
            runBlocking(Dispatchers.IO) {
                dataStoreRepositoryImpl.clearData().let {
                    it.collect { result ->
                        when (result) {
                            is Result.Success -> {
                                Log.d("clearDataStore: ", "clearDataStore: 성공")
                            }

                            is Result.Error -> {
                                Log.d("clearDataStore: ", "clearDataStore: 실패")
                            }

                            is Result.Loading -> {
                                Log.d("clearDataStore: ", "clearDataStore: 로딩중")
                            }
                        }
                    }
                }
            }

        private fun fetchToken() =
            runBlocking(Dispatchers.IO) {
                dataStoreRepositoryImpl.getToken().let { token ->
                    token.collect {
                        when (it) {
                            is Result.Success -> {
                                dataStoreRepositoryImpl.saveData("refresh_token", it.data?.refreshToken ?: "")
                                this@Intercept.token =
                                    Token(it.data?.refreshToken ?: "", this@Intercept.token.refreshToken)
                            }

                            is Result.Error -> {
                                this@Intercept.token = Token("", "")
                            }

                            is Result.Loading -> {
                                this@Intercept.token = Token("", "")
                            }
                        }
                    }
                }
            }

        private fun Interceptor.Chain.proceedWithToken(req: Request): Response =
            req.newBuilder()
                .addHeader(tokenHeader, "Bearer ${token.accessToken}")
                .build()
                .let(::proceed)
    }
