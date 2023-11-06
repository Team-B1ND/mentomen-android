package kr.hs.dgsw.mentomenv2.feature.auth.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.params.DAuthParam
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.repository.TokenRepository
import kr.hs.dgsw.smartschool.dodamdodam.widget.Event
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository
) : BaseViewModel() {
    private val _event = MutableSharedFlow<Event<String?>>()
    val event = _event.asSharedFlow()
    val code = MutableStateFlow<String>("")
    val tokenState = MutableStateFlow<Token>(Token("", ""))
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val pw = MutableStateFlow<String>("")
    val id = MutableStateFlow<String>("")

    fun onClickLogin() {
        authRepository.signIn(DAuthParam(
            id = id.value,
            pw = pw.value,
            clientId = "39bc523458c14eb987b7b16175426a31a9f105b7f5814f1f9eca7d454bd23c73",
            redirectUrl = "http://localhost:3000/callback"
        )).safeApiCall(
            isLoading = isLoading,
            successAction = {
                code.value = it!!.location!!.split("=", "&")[1]
                Log.d("onClickLogin: ", "로그인 성공")
                _event.tryEmit(Event("로그인 성공"))
            },
            errorAction = {
                Log.d("error", "error: $it")
            }
        )
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
