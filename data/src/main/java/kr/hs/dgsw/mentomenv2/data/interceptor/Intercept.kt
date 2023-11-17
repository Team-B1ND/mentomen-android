package kr.hs.dgsw.mentomenv2.data.interceptor

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import javax.inject.Inject

class Intercept @Inject constructor(
    private val tokenRepository: DataStoreRepository,
    private val coroutineScope: CoroutineScope
) : Interceptor {

    private var token: String = ""
    private lateinit var response: Response

    @Synchronized
    override fun intercept(chain: Interceptor.Chain): Response {
        coroutineScope.launch {
            tokenRepository.getData("access_token", "").let {
                it.collect {
                    token = it.data?: ""
                }
            }
        }

        response = chain.proceedWithToken(chain.request())

        if (response.code == 401) {
            coroutineScope.launch {
                tokenRepository.clearData()
            }
            chain.makeTokenRefreshCall()
            throw HttpException(retrofit2.Response.error<Any>(401, response.body!!))
        }

        return response
    }

    private fun Interceptor.Chain.makeTokenRefreshCall() {
        coroutineScope.launch {
            tokenRepository.getData("access_token", "").let {
                it.collect {
                    token = it.data?: ""
                }
            }
        }
        response = this.proceedWithToken(this.request())

        if (response.code == 401 || response.code == 500) { // 가끔식 토큰 만료에서 500이 뜨기도함.... 서버이슈
            coroutineScope.launch {
                tokenRepository.clearData()
            }
            response = login()
            throw HttpException(retrofit2.Response.error<Any>(401, response.body!!))
        }
    }

    private fun Interceptor.Chain.login(): Response {
        // 로그인으로 토큰 교체
//        getTokenToLogin()

        // request에 토큰을 붙여서 새로운 request 생성 -> 진행
        response = this.proceedWithToken(this.request())

        return if (response.code == 401 || response.code == 500) { // 가끔식 토큰 만료에서 500이 뜨기도함.... 서버이슈
            Log.d("TokenTest", "Here is Login")
            coroutineScope.launch {
                tokenRepository.clearData()
            }
            Response.Builder()
                .request(this.request())
                .protocol(Protocol.HTTP_1_1)
                .code(401)
                .message("세션이 만료되었습니다.")
                .body("세션이 만료되었습니다.".toResponseBody(null))
                .build()
        } else response
    }

    private fun Interceptor.Chain.proceedWithToken(req: Request): Response =
        req.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
            .let(::proceed)
}
