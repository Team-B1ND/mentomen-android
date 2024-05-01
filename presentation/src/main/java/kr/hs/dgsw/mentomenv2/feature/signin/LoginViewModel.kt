package kr.hs.dgsw.mentomenv2.feature.signin

import MutableEventFlow
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Code
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import kr.hs.dgsw.mentomenv2.domain.usecase.auth.GetCodeUseCase
import kr.hs.dgsw.mentomenv2.domain.util.Log
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import kr.hs.dgsw.mentomenv2.util.dauth.Client
import kr.hs.dgsw.mentomenv2.util.extractValueFromUrl
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val getCodeUseCase: GetCodeUseCase,
    private val authRepository: AuthRepository,
    private val dataStoreRepository: DataStoreRepository,
) : BaseViewModel() {
    val id = MutableStateFlow<String>("")
    val pw = MutableStateFlow<String>("")
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    fun login() {
        getCodeUseCase(id.value, pw.value, Client.CLIENT_ID, Client.REDIRECT_URL)
            .safeApiCall(
                isLoading = _isLoading,
                successAction = {
                    val code = extractValueFromUrl(it?.code ?: "", "code")
                    getToken(Code(code.toString()))
                },
            )
    }

    private fun getToken(code: Code) {
        authRepository.signIn(code)
            .safeApiCall(
                isLoading = _isLoading,
                {
                    Log.d("LoginViewModel", "Success")
                    it?.let {
                        saveToken(it)
                    }
                },
                {
                    viewEvent(FAILURE_LOGIN)
                }
            )
    }

    private fun saveToken(token: Token) {
        dataStoreRepository.saveToken(token).safeApiCall(
            _isLoading,
            {
                viewEvent(SUCCESS_LOGIN)
            },
            {
                viewEvent(FAILURE_LOGIN)
            },
        )
    }

    companion object {
        const val SUCCESS_LOGIN = 1
        const val FAILURE_LOGIN = 2
    }
}
