package kr.hs.dgsw.mentomenv2.feature.signin

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.repository.DAuthRepository
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import kr.hs.dgsw.mentomenv2.util.dauth.Client
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dAuthRepository: DAuthRepository,
) : BaseViewModel() {
    fun login() {
        viewModelScope.launch {
            dAuthRepository.getCode("", "", Client.CLIENT_ID, Client.REDIRECT_URL)
        }
    }
}
