package kr.hs.dgsw.mentomenv2.data.interceptor

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.mentomenv2.data.repository.DataStoreRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.usecase.auth.GetAccessTokenUseCase
import kr.hs.dgsw.mentomenv2.domain.util.Result
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONException
import retrofit2.HttpException
import javax.inject.Inject

class Intercept
@Inject
constructor(
    private val dataStoreRepositoryImpl: DataStoreRepositoryImpl,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : Interceptor {

    private val TOKEN_ERROR = 401
    private val TOKEN_HEADER = "Authorization"

    private lateinit var token: Token
    private lateinit var response: Response

    @Synchronized
    override fun intercept(chain: Interceptor.Chain): Response {
        setToken()
        response = chain.proceedWithToken(chain.request())

        if (response.code == TOKEN_ERROR) {
            response.close()
            chain.makeTokenRefreshCall()
        }

        return response
    }

    private fun Interceptor.Chain.makeTokenRefreshCall() {
        try {
            // Refresh Token으로 새로운 AccessToken 적립
            fetchToken()
        } catch (e: HttpException) {
            // 어떤 이유로 오류 발생 시
//            getTokenToLogin()
        }
        response = this.proceedWithToken(this.request())

        if (response.code == TOKEN_ERROR) {
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
        // 로그인으로 토큰 교체
//        getTokenToLogin()

        // request에 토큰을 붙여서 새로운 request 생성 -> 진행
        response = this.proceedWithToken(this.request())

        return if (response.code == TOKEN_ERROR) {
            Log.d("TokenTest", "Here is Login")
            Response.Builder()
                .request(this.request())
                .protocol(Protocol.HTTP_1_1)
                .code(TOKEN_ERROR)
                .message("세션이 만료되었습니다.")
                .body("세션이 만료되었습니다.".toResponseBody(null))
                .build()
        } else response
    }

    private fun setToken() = runBlocking(Dispatchers.IO) {
        dataStoreRepositoryImpl.getToken().let {
            it.collect {
                token = when (it) {
                    is Result.Success -> Token(
                        it.data?.accessToken ?: "",
                        it.data?.refreshToken ?: ""
                    )

                    is Result.Error -> Token("", "")
                    is Result.Loading -> Token("", "")
                }
            }
        }
    }

    private fun fetchToken() = runBlocking(Dispatchers.IO) {
        getAccessTokenUseCase.invoke().let {
            it.collect {
                when (it) {
                    is Result.Success -> {
                        dataStoreRepositoryImpl.saveData("refresh_token", it.data ?: "")
                        token = Token(it.data ?: "", token.refreshToken)
                    }

                    is Result.Error -> {
                        token = Token("", "")
                    }

                    is Result.Loading -> {
                        token = Token("", "")
                    }
                }
            }
        }
    }

    private fun Interceptor.Chain.proceedWithToken(req: Request): Response =
        req.newBuilder()
            .addHeader(TOKEN_HEADER, "Bearer ${token.accessToken}")
            .build()
            .let(::proceed)
}
