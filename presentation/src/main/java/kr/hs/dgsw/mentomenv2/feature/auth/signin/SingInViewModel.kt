package kr.hs.dgsw.mentomenv2.feature.auth.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.TokenRepository
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
) : BaseViewModel() {
    val tokenState = MutableStateFlow<Token>(Token("", ""))
    val pw = MutableStateFlow<String>("")
    val id = MutableStateFlow<String>("")

    fun setToken(token: Token) {
        viewModelScope.launch {
            tokenRepository.setToken(token)
        }
    }

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                tokenState.emit(tokenRepository.getToken())
                tokenState.collect {
                    Log.d("TOKEN", "accessToken: ${it.accessToken}\n refreshToken: ${it.refreshToken}")
                }
            }
        }
    }
}
