package kr.hs.dgsw.mentomenv2.feature.auth.signin

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.di.manager.DataStoreManager
import kr.hs.dgsw.mentomenv2.domain.datastore.PreferencesKeys
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.usecase.auth.SignInUseCase
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
) : BaseViewModel() {

    fun setToken(token: Token) {
        viewModelScope.launch {
            dataStoreManager.saveDataStore(PreferencesKeys.ACCESS_TOKEN, token.accessToken)
            dataStoreManager.saveDataStore(PreferencesKeys.REFRESH_TOKEN, token.refreshToken)

        }
    }
}
