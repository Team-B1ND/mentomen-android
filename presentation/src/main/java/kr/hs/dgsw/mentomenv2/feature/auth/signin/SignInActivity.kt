package kr.hs.dgsw.mentomenv2.feature.auth.signin

import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivitySignInBinding
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.loginWithDodam
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.settingDAuth

@AndroidEntryPoint
class SignInActivity() : BaseActivity<ActivitySignInBinding, SingInViewModel>(R.layout.activity_sign_in) {
    override val viewModel: SingInViewModel by viewModels()
    override fun start() {
        settingDAuth(R.string.clientId.toString(), R.string.clientSecret.toString(), R.string.redirectUrl.toString())
        loginWithDodam(applicationContext, {
            // 성공 시 처리할 로직을 입력해 주세요.
            Toast.makeText(
                this,
                "${it.accessToken}, ${it.refreshToken}",
                Toast.LENGTH_SHORT
            ).show()
        }, {
            // 실패 시 처리할 로직을 입력해 주세요.
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })
        binding.lifecycleOwner = this
    }
}
