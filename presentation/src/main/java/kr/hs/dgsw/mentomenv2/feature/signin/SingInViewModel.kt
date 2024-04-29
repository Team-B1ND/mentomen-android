package kr.hs.dgsw.mentomenv2.feature.signin

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Code
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import kr.hs.dgsw.mentomenv2.domain.util.Log
import javax.inject.Inject

@HiltViewModel
class SingInViewModel
    @Inject
    constructor(
        private val dataStoreRepository: DataStoreRepository,
        private val authRepository: AuthRepository,
    ) : BaseViewModel() {
        val tokenState = MutableStateFlow<Token>(Token("", ""))
        private val _isLoading = MutableStateFlow<Boolean>(false)
        val isLoading = _isLoading.asStateFlow()

        private fun setAccessToken(accessToken: String) {
            dataStoreRepository.saveData("access_token", accessToken)
                .safeApiCall(isLoading = _isLoading, successAction = {
                    Log.d(
                        "setToken: ",
                        "token save succes $accessToken",
                    )
                }, errorAction = {
                    Log.e(
                        "setToken: ",
                        "token save failure $accessToken",
                    )
                })
        }

        private fun setRefreshToken(refreshToken: String) {
            dataStoreRepository.saveData("refresh_token", refreshToken)
                .safeApiCall(isLoading = _isLoading, successAction = {
                    Log.d(
                        "setToken: ",
                        "refreshToken save success $refreshToken",
                    )
                }, errorAction = {
                    Log.e(
                        "setToken: ",
                        "refreshToken save failure $refreshToken}",
                    )
                })
        }

        fun getTokenUseCode(code: Code) {
            Log.d("getTokenUseCode: ", "getTokenUseCode: $code 호출됨")
            authRepository.signIn(code).safeApiCall(
                isLoading = _isLoading,
                successAction = {
                    tokenState.value = Token(it?.accessToken ?: "", it?.refreshToken ?: "")
                    Log.d(
                        "getTokenUseCode: success",
                        "access: ${it?.accessToken},refresh: ${it?.refreshToken}",
                    )
                    setAccessToken(it?.accessToken ?: "")
                    setRefreshToken(it?.refreshToken ?: "")
                    if (!it?.accessToken.isNullOrBlank() && !it?.refreshToken.isNullOrBlank()) {
                        viewEvent(START)
                    }
                },
                errorAction = {
                    setAccessToken("")
                    setRefreshToken("")
                    viewEvent(LOGIN)
                    Log.e("getTokenUseCode: error", "error: $it")
                },
            )
        }

        companion object {
            const val LOGIN = 1
            const val START = 2
        }
    }
