package kr.hs.dgsw.mentomenv2.feature.auth.signin

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
    val tokenLiveData = MutableStateFlow<Token>(Token("", ""))
    fun setToken(token: Token) {
        viewModelScope.launch {
            tokenRepository.setToken(token)
        }
    }

    init {
        viewModelScope.launch {
            tokenLiveData.emit(tokenRepository.getToken())
        }
    }
}
