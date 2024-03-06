package kr.hs.dgsw.mentomenv2.feature.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import kr.hs.dgsw.mentomenv2.domain.usecase.my.GetMyInfoUseCase
import javax.inject.Inject

@HiltViewModel
class SingInViewModel
    @Inject
    constructor(
        private val dataStoreRepository: DataStoreRepository,
        private val authRepository: AuthRepository,
        private val getMyInfoUseCase: GetMyInfoUseCase,
    ) : BaseViewModel() {
        val tokenState = MutableStateFlow<Token>(Token("", ""))
        private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
        val event = MutableSharedFlow<String>()

        fun checkToken() {
            getMyInfoUseCase.invoke().safeApiCall(
                null,
                {
                    viewModelScope.launch {
                        event.emit("Start")
                        dataStoreRepository.saveData("profile_image", it?.profileImage ?: "")
                        dataStoreRepository.saveData("name", it?.name ?: "")
                        dataStoreRepository.saveData("email", it?.email ?: "")
                        dataStoreRepository.saveData("grade", (it?.stdInfo?.grade ?: 0).toString())
                        dataStoreRepository.saveData("room", (it?.stdInfo?.room ?: 0).toString())
                        dataStoreRepository.saveData("number", (it?.stdInfo?.number ?: 0).toString())
                    }
                },
                {
                    viewModelScope.launch {
                        Log.d("checkToken: ", "토큰 검사(내 정보 불러오기) 실패 ㅠㅠ")
                        event.emit("Login")
                    }
                },
            )
        }

        fun getToken() {
            dataStoreRepository.getToken().safeApiCall(
                isLoading = isLoading,
                successAction = {
                    tokenState.value = Token(it?.accessToken ?: "", it?.refreshToken ?: "")
                    Log.d(
                        "singInViewModel getToken: StartSuccess",
                        "token: ${it?.accessToken ?: ""} + ${it?.refreshToken ?: ""}",
                    )
                    checkToken()
                },
                errorAction = {
                    tokenState.value = Token("", "")
                    viewModelScope.launch {

                        event.emit("Login")
                    }
                    Log.d(
                        "singInViewModel getToken: StartError",
                        "token: ${tokenState.value.accessToken} + ${tokenState.value.refreshToken}",
                    )
                },
            )
        }

        fun setAccessToken(accessToken: String) {
            Log.d("setToken: ", "호출 Token : $accessToken")
            dataStoreRepository.saveData("access_token", accessToken)
                .safeApiCall(isLoading = isLoading, successAction = {
                    Log.d(
                        "setToken: ",
                        "token save succes $accessToken",
                    )
                }, errorAction = {
                    Log.d(
                        "setToken: ",
                        "token save failure $accessToken",
                    )
                })
        }

        fun setRefreshToken(refreshToken: String) {
            dataStoreRepository.saveData("refresh_token", refreshToken)
                .safeApiCall(isLoading = isLoading, successAction = {
                    Log.d(
                        "setToken: ",
                        "refreshToken save success $refreshToken",
                    )
                }, errorAction = {
                    Log.d(
                        "setToken: ",
                        "refreshToken save failure $refreshToken}",
                    )
                })
        }

        fun getTokenUseCode(code: String?) {
            Log.d("getTokenUseCode: ", "getTokenUseCode: $code 호출됨")
            authRepository.signIn(code ?: "").safeApiCall(
                isLoading = isLoading,
                successAction = {
                    tokenState.value = Token(it?.accessToken ?: "", it?.refreshToken ?: "")
                    Log.d(
                        "getTokenUseCode: success",
                        "access: ${it?.accessToken},refresh: ${it?.refreshToken}",
                    )
                    viewModelScope.launch {
                        setAccessToken(it?.accessToken ?: "")
                        setRefreshToken(it?.refreshToken ?: "")
                        Log.d("getTokenUseCode: ", "getTokenUseCode: event emit 호출됨")
                        if (!it?.accessToken.isNullOrBlank() && !it?.refreshToken.isNullOrBlank()) {
                            event.emit("Start")
                        }
                    }
                },
                errorAction = {
                    viewModelScope.launch {
                        setAccessToken("")
                        setRefreshToken("")
                        event.emit("Login")
                    }
                    Log.d("getTokenUseCode: error", "error: $it")
                },
            )
        }
    }
