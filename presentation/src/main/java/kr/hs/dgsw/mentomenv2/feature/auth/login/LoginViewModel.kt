package kr.hs.dgsw.mentomenv2.feature.auth.login

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.mentomenv2.base.BaseViewModel

@HiltViewModel
class LoginViewModel : BaseViewModel() {
    val profileImage = MutableLiveData<String?>()
    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun onClickLogin() {

    }
}