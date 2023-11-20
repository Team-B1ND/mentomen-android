package kr.hs.dgsw.mentomenv2.feature.signin

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivitySignInBinding
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.feature.main.HomeActivity
import kr.hs.dgsw.mentomenv2.util.dauth.Client
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.getCode
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.settingDAuth

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding, SingInViewModel>() {
    override val viewModel: SingInViewModel by viewModels()
    var code: String? = null
    override fun start() {
        collectTokenState()
        settingDAuth(
            Client.clientId,
            Client.clientSecret,
            Client.redirectUri
        )
        setUpDAuth()
    }

    private fun collectTokenState() {
        lifecycleScope.launch {
            viewModel.tokenState.collect { tokenState ->
                Log.d(
                    "collectTokenState: ",
                    "Token 수집 성공 " + "token : " + tokenState.accessToken + tokenState.refreshToken
                )
                if (!tokenState.accessToken.isNullOrBlank() && !tokenState.refreshToken.isNullOrBlank()) {
                    viewModel.setToken(Token(tokenState.accessToken, tokenState.refreshToken))
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun setUpDAuth() {
        lifecycleScope.launch {
            viewModel.getToken()
            getCode(
                this@SignInActivity,
                {
                    Toast.makeText(this@SignInActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                    Log.d("autoLogin: ", "로그인 성공1")
                    viewModel.getTokenUseCode(it)
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                {
                    Log.d("autoLogin: ", "로그인 실패1")
                    getCode(
                        this@SignInActivity,
                        {
                            Log.d("autoLogin: ", "로그인 성공2")
                            viewModel.getTokenUseCode(it)
                            val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
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
