package kr.hs.dgsw.mentomenv2.feature.auth.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.repository.TokenRepository
import kr.hs.dgsw.mentomenv2.state.TokenState
import kr.hs.dgsw.smartschool.dodamdodam.widget.Event
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository
) : BaseViewModel() {
    private val _event = MutableSharedFlow<Event<String?>>()
    val event = _event.asSharedFlow()
    val tokenState = MutableStateFlow<TokenState>(TokenState("", "", false))
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val pw = MutableStateFlow<String>("")
    val id = MutableStateFlow<String>("")
    fun getToken() {
        tokenRepository.getToken().safeApiCall(
            isLoading = isLoading,
            successAction = { tokenState.value = TokenState(it!!.accessToken, it!!.refreshToken, true)
                Log.d("getToken: StartSuccess", "token: ${tokenState.value.accessToken} + ${ tokenState.value.refreshToken}")},
            errorAction = { tokenState.value = TokenState("", "", false)
                Log.d("getToken: StartError", "token: ${tokenState.value.accessToken} + ${ tokenState.value.refreshToken}") }
        ).launchIn(viewModelScope)
    }

    fun setToken(token: Token) {
        tokenRepository.setToken(token)
    }

    fun getTokenUseCode(code: String?) {
        authRepository.signIn(code ?: "").safeApiCall(
            isLoading = isLoading,
            successAction = {
                tokenState.value = TokenState(it?.accessToken ?: "", it?.refreshToken ?: "", true)
                Log.d("success", "access: ${it?.accessToken},refresh: ${it?.refreshToken}")
            },
            errorAction = {
                Log.d("error", "error: $it")
            }
        ).launchIn(viewModelScope)
    }

    init {
        tokenRepository.getToken().safeApiCall(
            successAction = {
                tokenState.value = TokenState(it?.accessToken ?: "", it?.refreshToken ?: "", true)
            },
            errorAction = {
                Log.d("error", "error: $it")
            }
        )
    }
}
