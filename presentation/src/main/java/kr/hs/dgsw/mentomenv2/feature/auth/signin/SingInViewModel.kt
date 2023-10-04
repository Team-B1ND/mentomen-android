package kr.hs.dgsw.mentomenv2.feature.auth.signin

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.usecase.auth.SignInUseCase
import java.security.MessageDigest
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val application: Application
) : BaseViewModel() {
    val profileImage = MutableLiveData<String?>()
    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun onClickLogin() {
        viewModelScope.launch {
            signInUseCase(
                SignInUseCase.DAuthParam(
                    id.value.toString(),
                    encryptSHA512(pw.value.toString()),
                    application.getString(R.string.clientId),
                    application.getString(R.string.redirectUrl)
                )
            )
                .onSuccess {
                    profileImage.value = it.profileImage
                }
                .onFailure {

                }
        }
    }

    private fun encryptSHA512(target: String): String {
        val messageDigest =
            MessageDigest.getInstance("SHA-512")
        val encryptedPassword = StringBuilder()
        messageDigest.update(target.toByteArray())
        val buffer = messageDigest.digest()
        for (temp in buffer) {
            var sb =
                StringBuilder(Integer.toHexString(temp.toInt()))
            while (sb.length < 2) {
                sb.insert(0, "0")
            }
            sb = StringBuilder(sb.substring(sb.length - 2))
            encryptedPassword.append(sb)
        }
        return encryptedPassword.toString()
    }
}