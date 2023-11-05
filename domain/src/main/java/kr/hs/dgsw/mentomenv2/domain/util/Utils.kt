package kr.hs.dgsw.mentomenv2.domain.util

import org.json.JSONObject
import retrofit2.HttpException

object Utils {
    const val TOKEN_EXCEPTION = "만료된 토큰"

    const val NETWORK_ERROR_MESSAGE = "서버에 도달할 수 없습니다. 네트워크 상태를 확인해 주세요."

    const val EXCEPTION = "알 수 없는 오류가 발생했습니다. 잠시만 기다려주세요."

    fun convertErrorBody(throwable: HttpException): String {
        return try {
            val errorBody = JSONObject(throwable.response()?.errorBody()!!.string())
            errorBody.getString("message")
        } catch (e: Exception) {
            "알 수 없는 오류가 발생했습니다. 잠시만 기다려주세요."
        }
    }
}
