package kr.hs.dgsw.mentomenv2.feature.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authRepository: AuthRepository
) : BaseViewModel() {
    val tokenState = MutableStateFlow<Token>(Token("", ""))
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    val event = MutableSharedFlow<Unit>()
    fun getToken() {
        dataStoreRepository.getToken().safeApiCall(
            isLoading = isLoading,
            successAction = {
                tokenState.value = Token(it?.accessToken ?: "", it?.refreshToken ?: "")
                Log.d(
                    "singInViewModel getToken: StartSuccess",
                    "token: ${it?.accessToken ?: ""} + ${it?.refreshToken ?: ""}"
                )
            },
            errorAction = {
                tokenState.value = Token("", "")
                Log.d(
                    "singInViewModel getToken: StartError",
                    "token: ${tokenState.value.accessToken} + ${tokenState.value.refreshToken}"
                )
            }
        ).launchIn(viewModelScope)
    }

    fun setToken(token: Token) {
        Log.d("setToken: ", "호출 Token : " + token.accessToken + token.refreshToken)
        dataStoreRepository.saveData("access_token", token.accessToken)
            .safeApiCall(isLoading = isLoading, successAction = {
                Log.d(
                    "setToken: ",
                    "token save succes ${token.accessToken} + ${token.refreshToken}"
                )
            }, errorAction = {
                Log.d(
                    "setToken: ",
                    "token save failure ${token.accessToken} + ${token.refreshToken}"
                )
            }).launchIn(viewModelScope)
        dataStoreRepository.saveData("refresh_token", token.refreshToken)
            .safeApiCall(isLoading = isLoading, successAction = {
                Log.d(
                    "setToken: ",
                    "token save succes ${token.refreshToken} + ${token.refreshToken}"
                )
            }, errorAction = {
                Log.d(
                    "setToken: ",
                    "token save failure ${token.accessToken} + ${token.refreshToken}"
                )
            }).launchIn(viewModelScope)
    }

    fun getTokenUseCode(code: String?) {
        Log.d("getTokenUseCode: ", "getTokenUseCode: $code 호출됨")
        authRepository.signIn(code ?: "").safeApiCall(
            isLoading = isLoading,
            successAction = {
                tokenState.value = Token(it?.accessToken ?: "", it?.refreshToken ?: "")
                Log.d(
                    "getTokenUseCode: success",
                    "access: ${it?.accessToken},refresh: ${it?.refreshToken}"
                )
                viewModelScope.launch {
                    setToken(Token(it?.accessToken ?: "", it?.refreshToken ?: ""))
                    Log.d("getTokenUseCode: ", "getTokenUseCode: event emit 호출됨")
                    event.emit(Unit)
                }
            },
            errorAction = {
                Log.d("getTokenUseCode: error", "error: $it")
            }
        ).launchIn(viewModelScope)
    }

    init {
        getToken()
    }
}
