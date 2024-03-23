package kr.hs.dgsw.mentomenv2.feature.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import kr.hs.dgsw.mentomenv2.domain.util.Log
import javax.inject.Inject

@HiltViewModel
class IntroViewModel
    @Inject
    constructor(
        private val dataStoreRepository: DataStoreRepository,
    ) : BaseViewModel() {
        val tokenState = MutableStateFlow<Token>(Token("", ""))
        private val _isLoading = MutableStateFlow<Boolean>(false)
        val isLoading = _isLoading.asStateFlow()

        fun getToken() {
            dataStoreRepository.getToken().safeApiCall(
                isLoading = _isLoading,
                successAction = {
                    tokenState.value = Token(it?.accessToken ?: "", it?.refreshToken ?: "")
                    Log.d(
                        "singInViewModel getToken Success",
                        "token: ${it?.accessToken ?: ""} + ${it?.refreshToken ?: ""}",
                    )
                    if (tokenState.value.accessToken.isNotEmpty() && tokenState.value.refreshToken.isNotEmpty()) {
                        viewEvent(START)
                    } else {
                        viewEvent(LOGIN)
                    }
                },
                errorAction = {
                    tokenState.value = Token("", "")
                    viewEvent(IntroViewModel.LOGIN)
                    Log.e(
                        "singInViewModel getToken: StartError",
                        "token: ${tokenState.value.accessToken} + ${tokenState.value.refreshToken}",
                    )
                },
            )
        }

        companion object Event {
            const val START = 1
            const val LOGIN = 2
        }
    }
