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
        autoLogin()
    }

    override fun onResume() {
        super.onResume()
        collectTokenState()
        autoLogin()
    }

    private fun collectTokenState() {
        lifecycleScope.launch {
            viewModel.tokenState.collect { tokenState ->
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

    private fun autoLogin() {
        lifecycleScope.launch {
            viewModel.getToken()
            getCode(
                this@SignInActivity,
                {
                    viewModel.getTokenUseCode(it)
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                },
                {
                    getCode(
                        this@SignInActivity,
                        {
                            viewModel.getTokenUseCode(it)
                            val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            finish()
                        },
                        {
                            Log.d("error: ", "how did you get here")
                        }
                    )
                }
            )
        }
    }
}