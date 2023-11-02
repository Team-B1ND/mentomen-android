package kr.hs.dgsw.mentomenv2.data.interceptor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.mentomenv2.domain.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import javax.inject.Inject

class Intercept @Inject constructor(
    private val tokenRepository: TokenRepository
) : Interceptor {

    private lateinit var token: String
    private lateinit var response: Response

    @Synchronized
    override fun intercept(chain: Interceptor.Chain): Response {
        runBlocking(Dispatchers.IO) {
            tokenRepository.getToken().let {
                token = it.accessToken
            }
        }
        response = chain.proceedWithToken(chain.request())

        if (response.code == 401) {
            response.close()
            chain.makeTokenRefreshCall()
        }

        return response
    }

    private fun Interceptor.Chain.makeTokenRefreshCall() {
        runBlocking(Dispatchers.IO) {
            tokenRepository.getToken().let {
                token = it.refreshToken
            }
        }
        response = this.proceedWithToken(this.request())

        if (response.code == 401) {
            try {
                response.close()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    private fun Interceptor.Chain.proceedWithToken(req: Request): Response =
        req.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
            .let(::proceed)
}
