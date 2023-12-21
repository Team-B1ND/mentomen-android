package kr.hs.dgsw.mentomenv2.feature.signin

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivitySignInBinding
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity
import kr.hs.dgsw.mentomenv2.util.dauth.Client
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.getCode
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.getRefreshToken
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.settingDAuth

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding, SingInViewModel>() {
    override val viewModel: SingInViewModel by viewModels()
    var code: String? = null


    override fun start() {
        collectTokenState()
        collectEvent()
        settingDAuth(
            Client.clientId,
            Client.clientSecret,
            Client.redirectUri
        )
        lifecycleScope.launch {
            viewModel.getToken()
            viewModel.tokenState.collect { token ->
                if (token.refreshToken != "") {
                    getRefreshToken(token.refreshToken,
                        Client.clientId,
                        onSuccess = {
                            Log.d("start: getRefreshToken Success", it.expiresIn + "token type : " + it.tokenType)
                            viewModel.setAccessToken(it.accessToken)
                        },
                        onFailure = {
                            Log.d("start: getRefreshToken : ", it.message.toString())
                        }
                    )
                }
            }
        }
        setUpDAuth()
    }

    private fun collectEvent() {
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                Log.d("collectEvent: in SignInActivity", "collect viewModels event")
                val intent = Intent(this@SignInActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun collectTokenState() {
        lifecycleScope.launch {
            viewModel.tokenState.collect { tokenState ->
                Log.d(
                    "collectTokenState: ",
                    "Token 수집 성공 " + "token : " + tokenState.accessToken + tokenState.refreshToken
                )
                if (!tokenState.accessToken.isNullOrBlank() && !tokenState.refreshToken.isNullOrBlank()) {
                    viewModel.event.emit(Unit)
                }
            }
        }
    }

    private fun setUpDAuth() {
        lifecycleScope.launch {
            getCode(
                this@SignInActivity,
                {
                    Log.d("autoLogin: ", "로그인 성공1")
                    viewModel.getTokenUseCode(it)
                },
                {
                    Log.d("autoLogin: ", "로그인 실패1")
                    getCode(
                        this@SignInActivity,
                        {
                            Log.d("autoLogin: ", "로그인 성공2")
                            viewModel.getTokenUseCode(it)
                        },
                        {
                            Log.d("autoLogin: ", "로그인 실패2 정상적인 방법으로 도달 불가능")
                        }
                    )
                }
            )
        }
    }
}
