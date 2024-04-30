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
import kr.hs.dgsw.mentomenv2.util.dauth.Client
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
        val event = MutableEventFlow<String>()
        private val _isLoading = MutableStateFlow<Boolean>(false)
        val isLoading = _isLoading.asStateFlow()

        fun login() {
            viewModelScope.launch {
                getCodeUseCase(id.value, pw.value, Client.CLIENT_ID, Client.REDIRECT_URL)
                    .safeApiCall(
                        isLoading = _isLoading,
                        {
                            val code = extractValueFromUrl(it?.code ?: "", "code")
                            Log.d("LoginViewModel", "Extracted code: $code")
                            getToken(Code(code.toString()))
                        },
                        {
                            viewModelScope.launch {
                                event.emit("로그인에 실패했습니다.")
                            }
                        },
                    )
            }
        }

        private fun getToken(code: Code) {
            viewModelScope.launch {
                authRepository.signIn(code)
                    .safeApiCall(
                        isLoading = _isLoading,
                        {
                            it?.let {
                                saveToken(it)
                            }
                        },
                    )
            }
        }

        fun extractValueFromUrl(
            url: String,
            paramName: String,
        ): String? {
            val regex = "$paramName=([^&]+)".toRegex()
            val matchResult = regex.find(url)
            return matchResult?.groups?.get(1)?.value
        }

        private fun saveToken(token: Token) {
            viewModelScope.launch {
                dataStoreRepository.saveToken(token).safeApiCall(
                    _isLoading,
                    {
                        viewModelScope.launch {
                            event.emit("로그인에 성공했습니다.")
                        }
                    },
                    {
                        viewModelScope.launch {
                            event.emit("로그인에 실패했습니다.")
                        }
                    },
                )
            }
        }
    }
