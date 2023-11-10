package kr.hs.dgsw.mentomenv2.feature.auth.signin

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivitySignInBinding
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.feature.main.HomeActivity
import kr.hs.dgsw.mentomenv2.state.TokenState
import kr.hs.dgsw.mentomenv2.util.dauth.Client
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.getCode
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.settingDAuth

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding, SingInViewModel>() {
    override val viewModel: SingInViewModel by viewModels()
    var code: String? = null
    private val _isAutoLogin = MutableLiveData<Boolean>(false)
    override fun start() {
        collectTokenState()
        lifecycleScope.launch {
            viewModel.getToken()

            Log.d("auth start: ", "${_isAutoLogin.value} + ${ viewModel.tokenState.value.accessToken}")
            if (!_isAutoLogin.value!!) {
                settingDAuth(
                    Client.clientId,
                    Client.clientSecret,
                    Client.redirectUri
                )
                getCode(
                    this@SignInActivity,
                    {
                        viewModel.getTokenUseCode(it)
                        Intent(this@SignInActivity, HomeActivity::class.java).apply {
                            startActivity(this)
                        }
                    },
                    {
                        getCode(
                            this@SignInActivity,
                            {
                                viewModel.getTokenUseCode(it)
                                Intent(this@SignInActivity, HomeActivity::class.java).apply {
                                    startActivity(this)
                                }
                            },
                            {
                                Log.d("error: ", "how did you get here")
                            }
                        )
                    }
                )
            } else {
                Intent(this@SignInActivity, HomeActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

    private fun collectTokenState() {
        lifecycleScope.launch {
            viewModel.tokenState.collect { tokenState ->
                if (!tokenState.accessToken.isNullOrBlank() && !tokenState.refreshToken.isNullOrBlank()) {
                    viewModel.setToken(Token(tokenState.accessToken, tokenState.refreshToken))
                    _isAutoLogin.value = true
                    Log.d("auth start in collect: ", "${_isAutoLogin.value} + ${ viewModel.tokenState.value.accessToken}")
                }
            }
        }
    }
}
