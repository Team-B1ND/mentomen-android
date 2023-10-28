package kr.hs.dgsw.mentomenv2.feature.auth.signin

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
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.loginWithDodam
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.settingDAuth

@AndroidEntryPoint
class SignInActivity() : BaseActivity<ActivitySignInBinding, SingInViewModel>() {
    override val viewModel: SingInViewModel by viewModels()

    override fun start() {
        settingDAuth(
            Client.clientId,
            Client.clientSecret,
            Client.redirectUri
        )
        lifecycleScope.launch {
            viewModel.tokenLiveData.collect {
                val isLogin: Boolean =
                    it.accessToken.isNullOrEmpty().not() && it.refreshToken.isNullOrEmpty().not()

                Log.d(
                    "start: 12341234",
                    isLogin.toString() + " " + it.accessToken + " " + it.refreshToken
                )
                if (isLogin) {
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    loginWithDodam(this@SignInActivity, {
                        val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        viewModel.setToken(Token(it.accessToken, it.refreshToken))
                        startActivity(intent)
                    }, {
                        loginWithDodam(this@SignInActivity, {
                            val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                            startActivity(intent)
                        }, {
                            Toast.makeText(this@SignInActivity, "실패입니다", Toast.LENGTH_SHORT).show()
                        })
                    })
                }
            }
        }
    }
}
