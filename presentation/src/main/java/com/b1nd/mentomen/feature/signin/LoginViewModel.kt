package com.b1nd.mentomen.feature.signin

import com.b1nd.mentomen.domain.model.Code
import com.b1nd.mentomen.base.BaseViewModel
import com.b1nd.mentomen.domain.model.Token
import com.b1nd.mentomen.domain.repository.AuthRepository
import com.b1nd.mentomen.domain.repository.DataStoreRepository
import com.b1nd.mentomen.domain.usecase.auth.GetCodeUseCase
import com.b1nd.mentomen.domain.util.Log
import com.b1nd.mentomen.util.dauth.Client
import com.b1nd.mentomen.util.extractValueFromUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
                    },
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
