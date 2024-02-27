package kr.hs.dgsw.mentomenv2.feature.signin

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivitySignInBinding
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity
import kr.hs.dgsw.mentomenv2.util.dauth.Client
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.getCode
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.settingDAuth

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding, SingInViewModel>() {
    override val viewModel: SingInViewModel by viewModels()
    var code: String? = null

    override fun start() {
        lifecycleScope.launch {
            collectTokenState()
            collectEvent()
            settingDAuth(
                Client.CLIENT_ID,
                Client.CLIENT_SECRET,
                Client.REDIRECT_URL,
            )
            viewModel.getToken()
        }
    }

    private fun collectEvent() {
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                if (event == "Start") {
                    Log.d("collectEvent: ", "$event collect viewModels event")
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else if (event == "Login") {
                    Log.d("collectEvent: ", "$event collect viewModels event")
                    setUpDAuth()
                }
            }
        }
    }

    private fun collectTokenState() {
        lifecycleScope.launch {
            viewModel.tokenState.collect { tokenState ->
                Log.d(
                    "collectTokenState: ",
                    "Token 수집 성공 " + "token : " + tokenState.accessToken + tokenState.refreshToken,
                )
                if (!tokenState.accessToken.isNullOrBlank() && !tokenState.refreshToken.isNullOrBlank()) {
                    viewModel.event.emit("Start")
                } else {
                    Log.d("123", "collectTokenState: 로그인 필요")
                    viewModel.event.emit("Login")
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
                    setUpDAuth()
                },
            )
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
