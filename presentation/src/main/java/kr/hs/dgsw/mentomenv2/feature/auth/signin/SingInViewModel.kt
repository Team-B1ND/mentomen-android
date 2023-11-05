package kr.hs.dgsw.mentomenv2.feature.auth.signin

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.params.DAuthParam
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.repository.TokenRepository
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository
) : BaseViewModel() {
    val tokenState = MutableStateFlow<Token>(Token("", ""))
    val pw = MutableStateFlow<String>("")
    val id = MutableStateFlow<String>("")

    fun onClickLogin() {
        authRepository.signIn(DAuthParam(
            id = id.value,
            pw = pw.value,
            clientId = "",
            redirectUrl = "",
            state = ""
        ))
        Log.d("pw", "pw: ${pw.value}")
        Log.d("id", "id: ${id.value}")
    }

    init {
        tokenRepository.getToken().safeApiCall(
            successAction = {
                tokenState.value = it?: Token("", "")
            },
            errorAction = {
                Log.d("error", "error: $it")
            }
        )
    }
}
