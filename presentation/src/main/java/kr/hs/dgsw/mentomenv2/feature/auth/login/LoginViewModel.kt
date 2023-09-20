package kr.hs.dgsw.mentomenv2.feature.auth.login

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.usecase.auth.SignInUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {
    val profileImage = MutableLiveData<String?>()
    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun onClickLogin() {

    }
}